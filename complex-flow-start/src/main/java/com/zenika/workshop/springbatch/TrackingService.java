/**
 * 
 */
package com.zenika.workshop.springbatch;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author acogoluegnes
 *
 */
public class TrackingService {
	
	private JdbcTemplate jdbcTemplate;
	
	public TrackingService(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void trackIncorrectFile(String name) {
		jdbcTemplate.update("insert into tracking (filename,reason) values (?,?)",
			name,"INCORRECT"
		);
	}
	
	public void trackFileWithSkips(String name) {
		jdbcTemplate.update("insert into tracking (filename,reason) values (?,?)",
			name,"SKIPS"
		);
	}
	
}
