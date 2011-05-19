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
public class ProductRowMapper implements RowMapper<Product> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Product(
				rs.getLong("id"),
				rs.getString("name"), 
				rs.getString("category")
		);
	}
}
