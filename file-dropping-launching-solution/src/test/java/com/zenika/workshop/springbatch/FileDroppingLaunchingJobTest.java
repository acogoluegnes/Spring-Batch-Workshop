/**
 * 
 */
package com.zenika.workshop.springbatch;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.Awaitility.to;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"/file-dropping-launching-job.xml","/integration-file.xml"
})
public class FileDroppingLaunchingJobTest {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private Job job;
	
	@Autowired
	private JobExplorer jobExplorer;
	
	@BeforeClass public static void init() throws Exception {
		EmbeddedFtpServer.start();
		FileUtils.cleanDirectory(new File(StartClient.CLIENT_INPUT_DIRECTORY));
	}
	
	@AfterClass public static void close() {
		EmbeddedFtpServer.stop();
	}
	
	@Before public void setUp() throws Exception {
		jdbcTemplate.update("delete from contact");
	}
	
	@Test public void fileDroppingLaunching() throws Exception {
		assertEquals(0, jobExplorer.getJobInstances(job.getName(), 0, Integer.MAX_VALUE).size());
		copyFileToFtpHome();
		await().untilCall(to(jdbcTemplate).queryForInt("select count(1) from contact"),equalTo(5));
		assertEquals(1, jobExplorer.getJobInstances(job.getName(), 0, Integer.MAX_VALUE).size());
	}

	private void copyFileToFtpHome() throws IOException {
		FileUtils.copyFileToDirectory(new File("./src/main/resources/contacts.txt"), new File(EmbeddedFtpServer.FTP_HOME_DIRECTORY));
	}
	
}
