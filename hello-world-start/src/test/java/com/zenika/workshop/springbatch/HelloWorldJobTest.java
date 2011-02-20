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
@ContextConfiguration("/hello-world-job.xml")
// TODO 04 remove the @Ignore annotation on the test
@Ignore
public class HelloWorldJobTest {

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Test public void helloWorld() throws Exception {
		// TODO 05 run the job with the job launcher. Use empty job parameters
		
		// TODO 06 check the returned JobExecution
		// it should have an ExitStatus.COMPLETED exit status
	}
	
}
