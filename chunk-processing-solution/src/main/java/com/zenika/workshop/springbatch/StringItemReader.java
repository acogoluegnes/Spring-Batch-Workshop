/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author acogoluegnes
 *
 */
public class StringItemReader implements ItemReader<String> {
	
	private List<String> list;
	
	public StringItemReader() {
		this.list = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7"));
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemReader#read()
	 */
	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		return !list.isEmpty() ? list.remove(0) : null;
	}

}
