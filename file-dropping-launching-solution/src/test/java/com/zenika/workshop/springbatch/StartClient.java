/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author acogoluegnes
 *
 */
public class StartClient {
	
	public static final String CLIENT_INPUT_DIRECTORY = "./input";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		File input = new File(CLIENT_INPUT_DIRECTORY);
		if(input.exists()) {
			FileUtils.cleanDirectory(new File(CLIENT_INPUT_DIRECTORY));
		}
		new ClassPathXmlApplicationContext("file-dropping-launching-job.xml","integration-file.xml");
		System.out.println("Client started.");
	}

}
