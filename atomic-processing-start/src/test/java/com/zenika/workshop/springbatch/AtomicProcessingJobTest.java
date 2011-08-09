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
@ContextConfiguration("/atomic-processing-job.xml")
public class AtomicProcessingJobTest {

	@Autowired
	private Job chunkOrientedJob;
	
	@Autowired
	private Job atomicProcessingJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
	}
	
	// TODO 02 launch the test. Check what both methods do and the expectations.
	
	@Test public void chunkOrientedProcessingFileOk() throws Exception {
		JobExecution execution = jobLauncher.run(chunkOrientedJob, 
				new JobParametersBuilder().addString("inputFile","classpath:/contacts-ok.txt")
					.toJobParameters());
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(25,jdbcTemplate.queryForInt("select count(1) from contact"));
	}
	
	@Test public void chunkOrientedProcessingFileNOk() throws Exception {
		JobExecution execution = jobLauncher.run(chunkOrientedJob, 
				new JobParametersBuilder().addString("inputFile","classpath:/contacts-nok.txt")
				.toJobParameters());
		assertEquals(ExitStatus.FAILED, execution.getExitStatus());
		assertEquals(20,jdbcTemplate.queryForInt("select count(1) from contact"));
	}
	
	// TODO 04 create two test methods to check the behavior of the "atomic" job
	// call these methods atomicProcessingFileOk and atomicProcessingFileNOk
	// they process the same respective file as the test methods of the chunk-oriented job
	
	
	// TODO 05 launch the test!
}
