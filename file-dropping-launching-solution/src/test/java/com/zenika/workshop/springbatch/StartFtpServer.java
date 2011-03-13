/**
 * 
 */
package com.zenika.workshop.springbatch;

/**
 * @author acogoluegnes
 *
 */
public class StartFtpServer {

	public static void main(String [] args) throws Exception {
		EmbeddedFtpServer.start();
		System.out.println("FTP started, press Enter to stop it...");
		System.in.read();
		EmbeddedFtpServer.stop();
		System.out.println("FTP stopped.");
	}
	
}
