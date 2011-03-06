/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertTrue;

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
	
	@Test public void scheduling() throws Exception {
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
