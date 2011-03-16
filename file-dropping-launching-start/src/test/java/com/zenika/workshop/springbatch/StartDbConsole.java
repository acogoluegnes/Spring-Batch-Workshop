/**
 * 
 */
package com.zenika.workshop.springbatch;

import java.sql.SQLException;

import org.h2.tools.Console;

/**
 * @author acogoluegnes
 *
 */
public class StartDbConsole {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO 08 launch the database console to check the content of the database
		Console.main("-web","-browser");
		// TODO 09 in the database console, enter "jdbc:h2:tcp://localhost/mem:file-dropping-launching" for the URL
		// "sa" for the user, and nothing in the password. Check the content of the contact table.
	}

}
