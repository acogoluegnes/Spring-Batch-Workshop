/**
 * 
 */
package com.zenika.workshop.springbatch.integration;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

/**
 * @author acogoluegnes
 *
 */
public class FileContactJobLauncher {
	
	private static final Logger LOG = LoggerFactory.getLogger(FileContactJobLauncher.class);

	private JobLauncher jobLauncher;
	
	private Job job;
	
	public void launch(File file) throws Exception {
		// TODO 01 take a look at the service activator that launches the job
		// it's going to be called by Spring Integration each time there's a new file on the FTP server
		LOG.info("starting import for file {}",file);
		JobExecution exec = jobLauncher.run(
			job, 
			new JobParametersBuilder()
				.addString("input.file", "file:"+file.getAbsolutePath())
				.addLong("time",System.currentTimeMillis())
				.toJobParameters()
		);
		LOG.info("job ended with status {}",exec);
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
}
