/**
 * 
 */
package com.zenika.workshop.springbatch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
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
@ContextConfiguration("/skip-job.xml")
public class SkipJobTest {

	@Autowired
	private Job noSkipJob;
	
	// TODO 06 declare a skipJob property (just like noSkip)
	// don't forget the @Autowired annotation!
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before public void setUp() {
		jdbcTemplate.update("delete from contact");
	}
	
	@Test public void noSkip() throws Exception {
		// TODO 01 launch the noSkipJob with empty job parameters
		
		// TODO 02 check the exit status of the execution is ExitStatus.FAILED
		
		// TODO 03 run the test!
		
		// TODO 04 take a look at the contacts.csv in src/main/resources 
		// try to spot the formatting error
	}
	
	@Test public void withSkip() throws Exception {
		// TODO 07 launch the skipJob with empty job parameters
		
		// TODO 08 check the exit status of the execution is ExitStatus.COMPLETED now
		
		// TODO 09 check with the jdbcTemplate that the correct number of contacts has been inserted
		// hint: use the queryForInt method on the contact table
		
		// TODO 10 run the test!
		
	}
	
}
