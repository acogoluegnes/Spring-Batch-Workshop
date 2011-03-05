/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.io.FileUtils;
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
@ContextConfiguration("/jdbc-paging-job.xml")
// TODO 01 remove the @Ignore annotation on the test
@Ignore
public class JdbcPagingJobTest {

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String outputFile = "./target/contacts.txt";
	
	@Before public void setUp() {
		File file = new File(outputFile);
		if(file.exists()) {
			file.delete();
		}
	}
	
	@Test public void jdbcPaging() throws Exception {
		JobExecution execution = jobLauncher.run(job, new JobParametersBuilder()
			.addString("output.file", "file:"+outputFile)
			.toJobParameters()
		);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		File file = new File(outputFile);
		assertTrue(file.exists());
		assertEquals(
			jdbcTemplate.queryForInt("select count(1) from contact"), 
			FileUtils.readLines(file).size()
		);
		// TODO 04 launch the test!
	}
	
}
