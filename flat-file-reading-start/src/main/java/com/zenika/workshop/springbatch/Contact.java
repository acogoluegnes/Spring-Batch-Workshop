/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.util.Date;

/**
 * @author acogoluegnes
 *
 */
public class Contact {

	private Long id;
	
	private String firstname,lastname;
	
	private Date birth;

	public Contact(String firstname, String lastname, Date birth) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birth = birth;
	}

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public Date getBirth() {
		return birth;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", birth=" + birth + "]";
	}
	
}
