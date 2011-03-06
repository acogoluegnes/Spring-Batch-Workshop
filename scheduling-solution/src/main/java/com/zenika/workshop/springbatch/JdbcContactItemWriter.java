/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author acogoluegnes
 *
 */
public class JdbcContactItemWriter implements ItemWriter<Contact> {
	
	private static final Logger LOG = LoggerFactory.getLogger(JdbcContactItemWriter.class);
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcContactItemWriter(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
	 */
	@Override
	public void write(final List<? extends Contact> items) throws Exception {
		LOG.info("writing {} contact(s) to the database",items.size());
		jdbcTemplate.batchUpdate(
			"insert into contact (id,firstname,lastname,birth) values (next value for contact_seq,?,?,?)", 
			new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, items.get(i).getFirstname());
					ps.setString(2, items.get(i).getLastname());
					ps.setDate(3, new Date(items.get(i).getBirth().getTime()));
				}
				
				@Override
				public int getBatchSize() {
					return items.size();
				}
			}
		);
	}

}
