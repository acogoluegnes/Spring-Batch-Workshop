/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
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
		JobExecution exec = jobLauncher.run(
			job,
			new JobParametersBuilder() 
				.addLong("time", System.currentTimeMillis())
				.toJobParameters()
		);
		LOG.info("job ended with status {}",exec);
	}
	
}
