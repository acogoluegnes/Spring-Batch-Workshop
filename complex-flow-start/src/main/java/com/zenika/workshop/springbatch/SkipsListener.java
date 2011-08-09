/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * @author acogoluegnes
 *
 */
public class SkipsListener implements StepExecutionListener {

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.StepExecutionListener#beforeStep(org.springframework.batch.core.StepExecution)
	 */
	@Override
	public void beforeStep(StepExecution stepExecution) { }

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.StepExecutionListener#afterStep(org.springframework.batch.core.StepExecution)
	 */
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO xx write SkipsListener
		// it checks if the exit code isn't FAILED and the number of skips
		// in this case, it returns a "COMPLETED WITH SKIPS" exit
		// it leaves the ExitStatus unchanged otherwise
		return null;
	}

}
