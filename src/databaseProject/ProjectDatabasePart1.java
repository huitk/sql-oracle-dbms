package databaseProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;

/*
 * Connect to Oracle DB using JDBC Driver.
 */
public class ProjectDatabasePart1 {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please provide the details to connect Oracle Database");
		System.out.println("Enter Database");
		String dbName = scanner.next();
		System.out.println("Enter UserName");
		String userName = scanner.next();
		System.out.println("Enter Password");
		String password = scanner.next();

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@artemis.vsnet.gmu.edu:1521/vse18c.vsnet.gmu.edu", userName,
					password);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (connection != null) {
			System.out.println("Successfullly connected to Oracle DB");
		} else {
			System.out.println("Failed to connect to Oracle DB");
		}
		
		/*Part 1 question 1 
		 *Write a program segment that retrieves the employees who work in the 
		 * Research department
		 * and print the employee’s last name and their SSN.
		 */ 
		try {
			String data = "";
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT lname, ssn FROM employee, department WHERE dno = dnumber AND dname = 'Research'");
			while(results.next()) {
				data = results.getString("lname");
				System.out.println("Last name: " + "row " + results.getRow() + " : " + data);
				data = results.getString("ssn");
				System.out.println("SSN:       " + "row " + results.getRow() + " : " + data);
				System.out.println();
				
			}
		} catch(SQLException e) {
			System.out.println("Could not retrieve data from the database " + e.getMessage());
		}
		System.out.println("END OF QUESTION 1");
		System.out.println();
		
		/* Part 1 question 2
		 * Write a program segment that retrieves the employees who work in departments 
		 * located in Houston and work on the project ‘ProductZ’. List their last name, 
		 * SSN, and the number of hours that the employee works on that project.
		 */
		try {
			String data = "";
			Statement statement = connection.createStatement();
			ResultSet results = statement.executeQuery("SELECT lname, ssn, hours FROM employee, dept_locations, project, works_on WHERE dno = dnumber AND dlocation = 'Houston' AND dnumber = dnum AND pnumber = pno AND ssn = essn AND pname = 'ProductZ'");
			while(results.next()) {
				data = results.getString("lname");
				System.out.println("Last name:             " + "row " + results.getRow() + " : " + data);
				data = results.getString("ssn");
				System.out.println("SSN:                   " + "row " + results.getRow() + " : " + data);
				data = results.getString("hours");
				System.out.println("Hours work on project: " + "row " + results.getRow() + " : " + data);
				
				System.out.println();
			}
		} catch(SQLException e) {
			System.out.println("Could not retrieve data from the database " + e.getMessage());
		}
		
	}

}