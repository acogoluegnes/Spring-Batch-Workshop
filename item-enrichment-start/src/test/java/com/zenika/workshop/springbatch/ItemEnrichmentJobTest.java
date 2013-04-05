/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mortbay.jetty.Server;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/item-enrichment-job.xml")
// TODO 06 remove the @Ignore annotation on the test
@Ignore
public class ItemEnrichmentJobTest {
	
	private static final Server server = new Server(8085);
	
	@BeforeClass
	public static void init() throws Exception {
		server.setHandler(new WebServiceHandler());
		server.start();
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		server.stop();
	}

	@Autowired
	private Job itemEnrichmentJob;
		
	// TODO 12 Autowire the asyncItemEnrichmentJob job	
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
	}
	
	// TODO 13 remove the @Ignore annotation on the method test
	@Ignore
	@Test public void warmingUp() throws Exception {
		for(int i=0;i<5;i++) {
			jobLauncher.run(itemEnrichmentJob, 
					new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
		}
	}
	
	@Test public void itemEnrichment() throws Exception {
		long start = System.currentTimeMillis();
		JobExecution execution = jobLauncher.run(itemEnrichmentJob, 
			new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
		// TODO 07 check the returned JobExecution
		// it should have an ExitStatus.COMPLETED exit status
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		System.out.println("sync job executed in "+(System.currentTimeMillis()-start)+" ms");
		// TODO 08 use the JdbcTemplate to check the content of the database.
		// take a look at the src/main/resources/contacts.txt 
		// to see how many contacts should be in the contact table.
		// hint: use the queryForInt method
		// TODO 09 call the checkSsnForContacts() method (check its content)
		
		// TODO 10 launch the test!
	}
	
	// TODO 14 create a test method and launch the asyncItemEnrichmentJob in the exact same way as the itemEnrichmentJob
	// be careful to use the asyncItemEnrichmentJob property when launching the job!
	// check the execution time of both jobs (the async version should be faster)
	
	
	private void checkSsnForContacts() {
		List<Map<String,Object>> contacts = jdbcTemplate.queryForList("select * from contact");
		for(Map<String,Object> item : contacts) {
			// US SSN is 9 digit long + 2 dashes
			Assert.assertTrue(item.get("ssn").toString().length() == 9 + 2);
			// last digit is the same as ID in our sample data
			Assert.assertTrue(item.get("ssn").toString().endsWith(item.get("id").toString()));
		}
	}
	
}
