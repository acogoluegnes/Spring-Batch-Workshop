/**
 * 
 */
package com.zenika.workshop.springbatch.integration;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.integration.ftp.session.AbstractFtpSessionFactory;

/**
 * Workaround for https://jira.springsource.org/browse/INT-1836
 * @author acogoluegnes
 *
 */
public class DefaultFtpSessionFactory extends AbstractFtpSessionFactory<FTPClient> {

	@Override
	protected FTPClient createClientInstance() {
		FTPClient client = new FTPClient();
		client.setDefaultPort(DefaultFtpSessionFactory.this.port);
		return client;
	}
	
}
