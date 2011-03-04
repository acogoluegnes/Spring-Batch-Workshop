/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author acogoluegnes
 * 
 */
public class ContactRowMapper implements RowMapper<Contact> {
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO 02 take a look at the ContactRowMapper
		// it implements the logic to convert the JDBC ResultSet into a domain object
		// the item reader will use it
		return new Contact(
				rs.getLong("id"),
				rs.getString("firstname"),
				rs.getString("lastname"), 
				rs.getDate("birth")
		);
	}
}
