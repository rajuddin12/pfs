package com.pfs.testengine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.pfs.reporting.ExecutionLog;
import com.pfs.test.base.TestBase;

public class testHelp {

	
	public static void main(String agr[]) throws Exception {
		String num = "78118803";
		
		String[] splited = num.split("\\.");
		System.out.println(num.concat("."));
	}
	
	@Test
	public void connectDB() throws SQLException {
		System.out.println("Started executing Queries");
		ExecutionLog.log("Started executing Queries");
		String url = "jdbc:mysql://localhost:3308/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String username = "apt";
		String password = "7NfbD7AZVB5y9tww";
		try (   // Step 1: Allocate a database 'Connection' object
				Connection conn = DriverManager.getConnection(url, username, password);  
				// Step 2: Allocate a 'Statement' object in the Connection
				Statement stmt = conn.createStatement();
		        ) {
			// Step 3: Execute a SQL SELECT query, the query result
			//  is returned in a 'ResultSet' object.
			String strSelect = "select * from security.user_payment_type";
			System.out.println("The SQL query is: " + strSelect);
			ResultSet rs=stmt.executeQuery(strSelect);  
			while(rs.next())  
				System.out.println(rs.getString(1));  
				conn.close();  
				
	}

	}
}
