/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.springframework.web.client.RestTemplate;

/**
 * @author acogoluegnes
 *
 */
public class ExecutionMetadataTest {
	
	private static final int PORT = 8081;
	
	private static final String CONTEXT_PATH = "/springbatchadmin";
	
	private static final String BASE_URL = String.format("http://localhost:%d%s/",PORT,CONTEXT_PATH);
	
	private static final String JOB = "flatFileReadingJob";
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Rule public RuleChain servers = RuleChain
				.outerRule(new H2Server())
				.around(new JettyServer(PORT,CONTEXT_PATH,"./src/main/webapp"));

	@Test public void submitJob() throws Exception {
		assertTrue(restTemplate.getForObject(BASE_URL+"jobs.json", String.class).contains(JOB));
	}
	
}
