/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
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
@ContextConfiguration("/item-processor-job.xml")
public class ItemProcessorJobTest {

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from registration_confirmation");
	}
	
	// TODO 07 remove the @Ignore annotation from the test method
	@Ignore
	@Test public void itemProcessor() throws Exception {
		// TODO 08 launch the test!
		JobExecution execution = jobLauncher.run(job, new JobParameters());
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(5,jdbcTemplate.queryForObject("select count(1) from registration_confirmation",Integer.class).intValue());
	}
	
}
