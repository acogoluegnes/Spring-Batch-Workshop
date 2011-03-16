/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.h2.tools.Server;
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
		// TODO 06 start the client
		Server.createTcpServer().start();
		File input = new File(CLIENT_INPUT_DIRECTORY);
		if(input.exists()) {
			FileUtils.cleanDirectory(new File(CLIENT_INPUT_DIRECTORY));
		}
		new ClassPathXmlApplicationContext("file-dropping-launching-job.xml","integration-file.xml");
		System.out.println("Client started.");
		// TODO 07 copy the contacts.txt file in src/main/resources to the ./ftphome directory
		// (the directory has just been created, refresh your project directory if you use Eclipse)
		// the file will be detected, downloaded from the FTP in the ./input, and processed by Spring Batch
		// (you should see some logs in the console)
	}

}
