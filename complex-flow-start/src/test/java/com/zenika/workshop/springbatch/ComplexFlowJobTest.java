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
// TODO xx remove the @Ignore annotation on the test
@Ignore
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
		// TODO check the execution completed successfully
		// TODO check there's the correct number of lines in the contact table
		// TODO check the tracking table is empty
		// TODO launch the test!
	}	
	
	// TODO xx remove the @Ignore annotation on the method test
	@Ignore
	@Test public void fileWithSkipped() throws Exception {
		JobExecution execution = jobLauncher.run(job,
			new JobParametersBuilder().addString("importFile","./src/main/resources/contacts-with-skips.txt")
				.toJobParameters()
		);
		// TODO check the execution completed successfully
		// TODO check there's the correct number of lines in the contact table
		// TODO check the fact the file has skips has been tracked correctly
		// TODO launch the test!		
	}
	
	// TODO xx remove the @Ignore annotation on the method test
	@Ignore
	@Test public void incorrectFile() throws Exception {
		// this file hasn't been downloaded correctly, the digest won't match
		JobExecution execution = jobLauncher.run(job,
			new JobParametersBuilder().addString("importFile","./src/main/resources/contacts-incorrect-file.txt")
				.toJobParameters()
		);
		// TODO check the execution completed successfully
		// TODO check there's the correct number of lines in the contact table (none!)		
		// TODO check the fact the file is incorrect has been tracked correctly
		// TODO launch the test!
	}
	
}
