/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author acogoluegnes
 *
 */
public class JdbcRegistrationConfirmationItemWriter implements ItemWriter<RegistrationConfirmation> {
	
	private JdbcTemplate jdbcTemplate;
	
	public JdbcRegistrationConfirmationItemWriter(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.item.ItemWriter#write(java.util.List)
	 */
	@Override
	public void write(List<? extends RegistrationConfirmation> items) throws Exception {
		// TODO 03 take a look at the writer: it inserts the confirmations inside the database
		for(RegistrationConfirmation item : items) {
			jdbcTemplate.update(
				"insert into registration_confirmation (number,contact_id,accepted) values (?,?,?)",
				item.getNumber(),item.getContact().getId(),item.isAccepted()
			);
		}
	}

}
