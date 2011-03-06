/**
 * 
 */
package com.zenika.workshop.springbatch;

/**
 * @author acogoluegnes
 *
 */
public class RegistrationConfirmation {

	private String number;
	
	private boolean accepted;
	
	private Contact contact;

	public RegistrationConfirmation(String number, Contact contact,boolean accepted) {
		super();
		this.number = number;
		this.contact = contact;
		this.accepted = accepted;
	}

	public String getNumber() {
		return number;
	}

	public Contact getContact() {
		return contact;
	}
	
	public boolean isAccepted() {
		return accepted;
	}
	
}
