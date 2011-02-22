/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author acogoluegnes
 *
 */
public class StringItemProcessor implements ItemProcessor<String, String> {

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemProcessor#process(java.lang.Object)
	 */
	@Override
	public String process(String item) throws Exception {
		return "*** "+item+" ***";
	}

}
