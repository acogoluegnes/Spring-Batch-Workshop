/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.jetty.handler.AbstractHandler;

/**
 * @author acogoluegnes
 *
 */
public class WebServiceHandler extends AbstractHandler {
	
	private Map<String,String> ssnDb = new HashMap<String, String>() {{
		put("De-Anna.Raghunath","987-65-4321");
		put("Susy.Hauerstock","987-65-4322");
		put("Kiam.Whitehurst","987-65-4323");
		put("Alecia.Van Holst","987-65-4324");
		put("Hing.Senecal","987-65-4325");
	}};	

	/* (non-Javadoc)
	 * @see org.mortbay.jetty.Handler#handle(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, int)
	 */
	@Override
	public void handle(String target, HttpServletRequest request,
			HttpServletResponse response, int dispatch) throws IOException,
			ServletException {
		simulateLatency();
		response.setContentType("text/xml");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print("<contact>" +
				"<firstname>"+firstname+"</firstname>" +
				"<lastname>"+lastname+"</lastname>" +
				"<ssn>"+getSsn(firstname,lastname)+"</ssn>" +
				"</contact>"
		);
		response.flushBuffer();
	}

	private void simulateLatency() throws ServletException {
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			throw new ServletException("interruption while simulating latency",e);
		}
	}

	private String getSsn(String firstname,String lastname) {
		return ssnDb.get(firstname+"."+lastname);
	}

}
