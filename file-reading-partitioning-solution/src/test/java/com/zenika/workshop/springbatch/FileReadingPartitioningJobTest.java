/**
 * 
 */
package com.zenika.workshop.springbatch;

import static org.junit.Assert.assertEquals;

import org.h2.tools.Server;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acogoluegnes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/file-reading-partitioning-job.xml")
public class FileReadingPartitioningJobTest {

	@Autowired
	private Job fileReadingPartitioningJob;
	
	@Autowired
	private Job fileReadingJob;
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Server db;
	
	@BeforeClass
	public static void startDb() throws Exception {
		db = Server.createTcpServer().start();
	}
	
	@AfterClass
	public static void stopDb() throws Exception {
		db.stop();
	}
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
	}
	
	@Test public void fileReadingPartitioning() throws Exception {
		long start = System.currentTimeMillis();
		JobExecution execution = jobLauncher.run(fileReadingPartitioningJob, 
				new JobParametersBuilder().addLong("time", start).toJobParameters()
		);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(4*100,jdbcTemplate.queryForObject("select count(1) from contact",Integer.class).intValue());
		System.out.println("partitioned: "+(System.currentTimeMillis()-start)+" ms");
	}	
	
	@Test public void fileReadingNoPartitioning() throws Exception {
		long start = System.currentTimeMillis();
		JobExecution execution = jobLauncher.run(fileReadingJob,
				new JobParametersBuilder().addLong("time", start).toJobParameters()
		);
		assertEquals(ExitStatus.COMPLETED, execution.getExitStatus());
		assertEquals(4*100,jdbcTemplate.queryForObject("select count(1) from contact",Integer.class).intValue());
		System.out.println("not partitioned: "+(System.currentTimeMillis()-start)+" ms");
	}
	
	
	
	
	
	
	
	
	
}
