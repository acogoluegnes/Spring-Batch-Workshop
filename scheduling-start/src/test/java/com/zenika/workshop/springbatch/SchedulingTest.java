/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertTrue;

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
		long wait = 1000;
		long maxWait = 10000;
		while(getJobInstancesNumber() <= expectedJobInstances && wait <= maxWait) {
			wait += wait;
			Thread.sleep(wait);
		}
		assertTrue(getJobInstancesNumber() >= expectedJobInstances);
	}
	
	private int getJobInstancesNumber() {
		return jobExplorer.getJobInstances(job.getName(), 0, Integer.MAX_VALUE).size();
	}
	
}
