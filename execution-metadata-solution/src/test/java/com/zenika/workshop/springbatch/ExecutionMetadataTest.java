/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.springframework.web.client.RestTemplate;

/**
 * @author acogoluegnes
 *
 */
public class ExecutionMetadataTest {
	
	private static final String BASE_URL = "http://localhost:8081/springbatchadmin/";
	
	private static final String JOB = "flatFileReadingJob";
	
	private RestTemplate restTemplate = new RestTemplate();

	@Test public void submitJob() throws Exception {
		org.h2.tools.Server h2 = org.h2.tools.Server.createTcpServer(); 
		Server server = new Server();
		Connector connector = new SelectChannelConnector();
		connector.setPort(8081);
		connector.setHost("127.0.0.1");
		server.addConnector(connector);

		WebAppContext wac = new WebAppContext();
		wac.setContextPath("/springbatchadmin");
		wac.setWar("./src/main/webapp");
		server.setHandler(wac);
		server.setStopAtShutdown(true);
		
		try {
			h2.start();
			server.start();
			assertTrue(restTemplate.getForObject(BASE_URL+"jobs.json", String.class).contains(JOB));
		} finally {
			server.stop();
			h2.stop();
		}
	}
	
}
