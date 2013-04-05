/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@ContextConfiguration("/complex-flow-job.xml")
public class ComplexFlowJobTest {

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
		jdbcTemplate.update("delete from tracking");
	}
	
	@Test public void correctFile() throws Exception {
		JobExecution execution = jobLauncher.run(job,
			new JobParametersBuilder().addString("importFile","./src/main/resources/contacts-correct-file.txt")
				.toJobParameters()
		);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(5,jdbcTemplate.queryForObject("select count(1) from contact",Integer.class).intValue());
		assertEquals(0,jdbcTemplate.queryForObject("select count(1) from tracking",Integer.class).intValue());
	}
	
	@Test public void incorrectFile() throws Exception {
		JobExecution execution = jobLauncher.run(job,
			new JobParametersBuilder().addString("importFile","./src/main/resources/contacts-incorrect-file.txt")
				.toJobParameters()
		);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(0,jdbcTemplate.queryForObject("select count(1) from contact",Integer.class).intValue());
		assertEquals(1,jdbcTemplate.queryForObject("select count(1) from tracking where reason = ?",Integer.class,"INCORRECT").intValue());
	}
	
	@Test public void fileWithSkipped() throws Exception {
		JobExecution execution = jobLauncher.run(job,
			new JobParametersBuilder().addString("importFile","./src/main/resources/contacts-with-skips.txt")
				.toJobParameters()
		);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(5-1,jdbcTemplate.queryForObject("select count(1) from contact",Integer.class).intValue());
		assertEquals(1,jdbcTemplate.queryForObject("select count(1) from tracking where reason = ?",Long.class,"SKIPS").intValue());
	}
	
}
