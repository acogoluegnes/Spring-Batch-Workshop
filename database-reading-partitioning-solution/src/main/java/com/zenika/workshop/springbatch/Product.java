/**
 * 
 */
package com.zenika.workshop.springbatch;


/**
 * @author acogoluegnes
 *
 */
public class Product {

	private final Long id;
	
	private final String name;
	
	private final String category;
	
	public Product(Long id, String name, String category) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

}
