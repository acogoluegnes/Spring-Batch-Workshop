/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.junit.Before;
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

import static org.junit.Assert.assertEquals;

/**
 * @author acogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/skip-job.xml")
public class SkipJobTest {

	@Autowired
	private Job noSkipJob;
	
	@Autowired
	private Job skipJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
	}
	
	@Test public void noSkip() throws Exception {
		JobExecution execution = jobLauncher.run(noSkipJob, new JobParameters());
		assertEquals(ExitStatus.FAILED, execution.getExitStatus());
	}
	
	@Test public void withSkip() throws Exception {
		JobExecution execution = jobLauncher.run(skipJob, new JobParameters());
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(7,jdbcTemplate.queryForObject("select count(1) from contact",Integer.class).intValue());
	}
	
}
