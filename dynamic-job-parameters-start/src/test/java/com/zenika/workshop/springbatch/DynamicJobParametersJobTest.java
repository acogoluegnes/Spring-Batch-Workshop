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
@ContextConfiguration("/dynamic-job-parameters-job.xml")
// TODO 01 remove the @Ignore annotation on the test
@Ignore
public class DynamicJobParametersJobTest {

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
	}
	
	@Test public void dynamicJobParameters() throws Exception {
		// TODO 02 take a look at the job parameter(s) to launch the job  
		JobParameters jobParameters = new JobParametersBuilder()
			.addString("input.file", "file:./input/contacts-01.txt")
			.toJobParameters();
		JobExecution execution = jobLauncher.run(job, jobParameters);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(7,jdbcTemplate.queryForInt("select count(1) from contact"));
		
		jobParameters = new JobParametersBuilder()
			.addString("input.file", "file:./input/contacts-02.txt")
			.toJobParameters();
		execution = jobLauncher.run(job, jobParameters);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(13,jdbcTemplate.queryForInt("select count(1) from contact"));
		// TODO 04 launch the test!
	}
	
}
