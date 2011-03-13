/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

/**
 * @author acogoluegnes
 *
 */
public class EmbeddedFtpServer {
	
	private static FtpServer ftpServer;
	
	public static final String FTP_HOME_DIRECTORY = "./ftphome";

	public static final void start() throws Exception {
		FileUtils.forceMkdir(new File(FTP_HOME_DIRECTORY));
		FileUtils.cleanDirectory(new File(FTP_HOME_DIRECTORY));
		
		FtpServerFactory serverFactory = new FtpServerFactory();
		
		ListenerFactory factory = new ListenerFactory();        
		factory.setPort(2222);
		serverFactory.addListener("default", factory.createListener());
		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
        UserManager um = userManagerFactory.createUserManager();
        BaseUser user = new BaseUser();
        user.setName("admin");
        user.setPassword("admin");
        user.setEnabled(true);
        user.setHomeDirectory(FTP_HOME_DIRECTORY);
        um.save(user);
        
        serverFactory.setUserManager(um);
        
        ftpServer = serverFactory.createServer(); 
		ftpServer.start();
	}
	
	public static void stop() {
		ftpServer.stop();
	}
	
}
