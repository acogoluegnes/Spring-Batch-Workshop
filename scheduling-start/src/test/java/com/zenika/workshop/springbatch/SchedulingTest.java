/**
 * 
 */
package com.zenika.workshop.springbatch;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.to;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/scheduling-job.xml")
public class SchedulingTest {
	
	@Autowired
	private JobExplorer jobExplorer;
	
	@Autowired
	private Job job;
	
	// TODO 04 remove the @Ignore annotation from the test method
	@Ignore
	@Test public void scheduling() throws Exception {
		// TODO 05 launch the test!
		// the test waits for 5 instances of the job to be executed
		int expectedJobInstances = 5;
		// default timeout is 10 s
		await().untilCall(to(this).getJobInstancesNumber(),equalTo(expectedJobInstances));
	}
	
	public int getJobInstancesNumber() {
		return jobExplorer.getJobInstances(job.getName(), 0, Integer.MAX_VALUE).size();
	}
	
}
