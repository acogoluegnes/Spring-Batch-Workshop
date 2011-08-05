/**
 * 
 */
package com.zenika.workshop.springbatch;

import javax.xml.transform.dom.DOMSource;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.NodeList;

/**
 * @author acogoluegnes
 *
 */
public class SsnWebServiceItemProcessor implements
		ItemProcessor<Contact, Contact> {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private String url;

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
	 */
	@Override
	public Contact process(Contact item) throws Exception {
		DOMSource source = restTemplate.getForObject(url, DOMSource.class, item.getFirstname(),item.getLastname());
		String ssn = extractSsnFromXml(item, source);
		item.setSsn(ssn);
		return item;
	}

	private String extractSsnFromXml(Contact item, DOMSource source) {
		NodeList nodeList = source.getNode().getChildNodes().item(0).getChildNodes();
		for(int i=0;i<nodeList.getLength();i++) {
			if("ssn".equals(nodeList.item(i).getNodeName())) {
				return nodeList.item(i).getTextContent();
			}
		}
		return null;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
