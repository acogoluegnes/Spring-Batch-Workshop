/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author acogoluegnes
 *
 */
public class ProductCategoryPartitioner implements Partitioner {
	
	private final JdbcOperations jdbcTemplate;
	
	public ProductCategoryPartitioner(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/* (non-Javadoc)
	 * @see org.springframework.batch.core.partition.support.Partitioner#partition(int)
	 */
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		// TODO 01 finish the partitioner (2 steps)
		
		// step 1: get the product categories, one category = one partition
		// hint: take a look at src/main/resources/create-tables.sql 
		// to see the structure of the table
		List<String> categories = jdbcTemplate.queryForList(
			"",
			String.class
		);
		Map<String, ExecutionContext> results = new LinkedHashMap<String, ExecutionContext>();
		for(String category : categories) {
			ExecutionContext context = new ExecutionContext();
			// step 2: put the partition information in the context
			results.put("partition."+category, context);
		}
		return results;
	}

}
