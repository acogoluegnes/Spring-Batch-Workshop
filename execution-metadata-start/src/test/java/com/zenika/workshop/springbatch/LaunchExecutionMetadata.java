/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.h2.tools.Console;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;


/**
 * @author acogoluegnes
 *
 */
public class LaunchExecutionMetadata {

	public static void main(String [] args) throws Exception {
		// TODO 02 launch this class a plain Java program
		// it starts the database and the Spring Batch Admin web app
		Console.main(args);
		
		Server server = new Server();
		Connector connector = new SelectChannelConnector();
		connector.setPort(8080);
		connector.setHost("127.0.0.1");
		server.addConnector(connector);

		WebAppContext wac = new WebAppContext();
		wac.setContextPath("/springbatchadmin");
		wac.setWar("./src/main/webapp");
		server.setHandler(wac);
		server.setStopAtShutdown(true);
		
		server.start();
        
        System.out.println("**** Spring Batch Admin launched");
        
        // TODO 03 go to http://localhost:8080/springbatchadmin/
        // TODO 04 click on "Jobs" and select the only job
        // TODO 05 the "Job Parameters" input, enter input.file=file:./input/contacts-01.txt and click on "Launch"
        // this starts a new instance of the job, with the contacts-01.txt file as a parameter
        // TODO 06 click on "executions" to see the executions of the newly created job instance
        // the instance is completed, as the execution succeeded 
        // you can follow the link to see how many records have been inserted
        // TODO 07 go back to http://localhost:8080/springbatchadmin/jobs/flatFileReadingJob
        // TODO 08 in the "Job Parameters" input, enter input.file=file:./input/contacts-02.txt and click on "Launch"
        // the execution fails as there's a bad line in the file
        // TODO 09 correct the contacts-02.txt file and restart the job execution
        // TODO 10 check the instance is completed
        // TODO 11 check that the correct number of records have been inserted in the database
        // hint: go to the H2 web console:
        //   - URL: jdbc:h2:tcp://localhost/mem:execution_metadata
        //   - User Name: sa
        //   - Password:
        // check the content of the "contact" table
	}
	
	
}
