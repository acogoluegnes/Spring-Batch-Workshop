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
public class DigestListener implements StepExecutionListener {

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
		if(hasStepExecutionFailed(stepExecution)) {
			return new ExitStatus("INCORRECT FILE", "digest comparison failed");
		} else {
			return stepExecution.getExitStatus();
		}
	}

	private boolean hasStepExecutionFailed(StepExecution stepExecution) {
		return ExitStatus.FAILED.getExitCode().equals(stepExecution.getExitStatus().getExitCode());
	}

}
