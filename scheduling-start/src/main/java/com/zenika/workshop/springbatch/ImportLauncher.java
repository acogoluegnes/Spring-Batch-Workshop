/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author acogoluegnes
 *
 */
public class ImportLauncher {
	
	private static final Logger LOG = LoggerFactory.getLogger(ImportLauncher.class);

	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	public void launch() throws Exception {
		// TODO 01 launch the job with a Long parameter = System.currentTimeMillis())
		// hint: use the JobParametersBuilder class and the addLong method to create the job parameters
		JobExecution exec = null;
		LOG.info("job ended with status {}",exec);
	}
	
}
