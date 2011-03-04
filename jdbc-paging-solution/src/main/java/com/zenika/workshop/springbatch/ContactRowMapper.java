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
		return new Contact(
				rs.getLong("id"),
				rs.getString("firstname"),
				rs.getString("lastname"), 
				rs.getDate("birth")
		);
	}
}
