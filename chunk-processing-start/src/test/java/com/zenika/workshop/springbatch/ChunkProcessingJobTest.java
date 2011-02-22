/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/chunk-processing-job.xml")
// TODO 07 remove the @Ignore annotation on the test
@Ignore
public class ChunkProcessingJobTest {

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Test public void helloWorld() throws Exception {
		// TODO 08 run the job with the job launcher. Use empty job parameters
		
		// TODO 09 check the returned JobExecution
		// it should have an ExitStatus.COMPLETED exit status
		
		// TODO 10 run the test! you should see the decorated Strings on the console
	}
	
}
