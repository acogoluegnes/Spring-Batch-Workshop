/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.junit.rules.ExternalResource;

/**
 * @author acogoluegnes
 *
 */
public class H2Server extends ExternalResource {
	
	private org.h2.tools.Server h2;
	
	@Override
	protected void before() throws Throwable {
		h2 = org.h2.tools.Server.createTcpServer(); 
		h2.start();
	}
	
	@Override
	protected void after() {
		h2.stop();
	}
	
}
