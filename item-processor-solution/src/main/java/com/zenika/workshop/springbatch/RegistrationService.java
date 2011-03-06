/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @author acogoluegnes
 *
 */
public class RegistrationService {

	public RegistrationConfirmation process(Contact contact) {
		return new RegistrationConfirmation(
			UUID.randomUUID().toString(),
			contact,
			RandomUtils.nextBoolean()
		);
	}
	
}
