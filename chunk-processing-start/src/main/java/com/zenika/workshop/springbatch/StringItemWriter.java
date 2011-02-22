/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

/**
 * @author acogoluegnes
 *
 */
public class StringItemWriter implements ItemWriter<String> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StringItemWriter.class);

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
	 */
	@Override
	public void write(List<? extends String> items) throws Exception {
		// TODO 04 output items one after the other with the LOGGER
	}

}
