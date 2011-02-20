/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * @author acogoluegnes
 *
 */
public class HelloWorldTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		// TODO 01 print "Hello World!" on the console
		
		// TODO 02 return RepeatStatus.FINISHED
		// Spring Batch will execute the method once then
		return null;
	}
	
}
