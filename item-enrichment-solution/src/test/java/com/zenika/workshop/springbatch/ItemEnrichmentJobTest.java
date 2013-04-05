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
	
	@Autowired
	private Job asyncItemEnrichmentJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
	}
	
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
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		System.out.println("sync job executed in "+(System.currentTimeMillis()-start)+" ms");		
		assertEquals(5,jdbcTemplate.queryForInt("select count(1) from contact"));
		List<Map<String,Object>> contacts = jdbcTemplate.queryForList("select * from contact");
		for(Map<String,Object> item : contacts) {
			Assert.assertTrue(item.get("ssn").toString().length() == 9 + 2);
			Assert.assertTrue(item.get("ssn").toString().endsWith(item.get("id").toString()));
		}
	}
	
	@Test public void asyncItemEnrichment() throws Exception {
		long start = System.currentTimeMillis();
		JobExecution execution = jobLauncher.run(asyncItemEnrichmentJob, 
			new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		System.out.println("async job executed in "+(System.currentTimeMillis()-start)+" ms");
		assertEquals(5,jdbcTemplate.queryForInt("select count(1) from contact"));
		List<Map<String,Object>> contacts = jdbcTemplate.queryForList("select * from contact");
		for(Map<String,Object> item : contacts) {
			Assert.assertTrue(item.get("ssn").toString().length() == 9 + 2);
			Assert.assertTrue(item.get("ssn").toString().endsWith(item.get("id").toString()));
		}
	}
	
}
