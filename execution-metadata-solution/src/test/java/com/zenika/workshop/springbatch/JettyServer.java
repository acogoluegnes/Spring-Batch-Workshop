/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.junit.rules.ExternalResource;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * @author acogoluegnes
 *
 */
public class JettyServer extends ExternalResource {
	
	private Server server;
	
	private final int port;
	
	private final String contextPath;
	
	private final String war;
	
	public JettyServer(int port, String contextPath, String war) {
		super();
		this.port = port;
		this.contextPath = contextPath;
		this.war = war;
	}
	
	@Override
	protected void before() throws Throwable {
		server = new Server();
		Connector connector = new SelectChannelConnector();
		connector.setPort(port);
		connector.setHost("127.0.0.1");
		server.addConnector(connector);

		WebAppContext wac = new WebAppContext();
		wac.setContextPath(contextPath);
		wac.setWar(war);
		server.setHandler(wac);
		server.setStopAtShutdown(true);
		
		server.start();
	}

	@Override
	protected void after() {
		try {
			server.stop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
