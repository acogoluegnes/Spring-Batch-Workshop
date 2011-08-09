/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.util.DigestUtils;

/**
 * @author acogoluegnes
 *
 */
public class DigestTasklet implements Tasklet {
	
	private String importFile;

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.step.tasklet.Tasklet#execute(org.springframework.batch.core.StepContribution, org.springframework.batch.core.scope.context.ChunkContext)
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		String expectedDigest = FileUtils.readFileToString(new File(importFile+".md5"), "UTF-8");		
		String calculatedDigest = DigestUtils.md5DigestAsHex(FileUtils.readFileToByteArray(new File(importFile)));
		if(!calculatedDigest.equals(expectedDigest)) {
			throw new IllegalArgumentException("Bad digest for file "+importFile);
		}
		return RepeatStatus.FINISHED;
	}
	
	public void setImportFile(String importFile) {
		this.importFile = importFile;
	}

}
