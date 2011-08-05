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
	
	private String ssn;

	public Contact(Long id,String firstname, String lastname, Date birth) {
		super();
		this.id = id;
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
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", birth=" + birth + ", ssn=" + ssn + "]";
	}
	
}
