package com.pfs.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pfs.reporting.ExecutionLog;


public class DBConnection {

//	DEV
	static String url = "jdbc:mysql://localhost:3307/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String username = "aptsa";
	static String password = "ELHl3XT6LaDfCEItos989d0NiaX5CoAXhZrF7y4S";
	
//	QA
//	static String url = "jdbc:mysql://localhost:3308/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//	static String username = "apt";
//	static String password = "7NfbD7AZVB5y9tww";
	static Connection conn;
	static Statement stmt ;


	/**
	 * Connect to Data Base
	 * @param query
	 * @throws SQLException
	 */
	public static void connectDB() throws SQLException {
		try {
			// Step 1: Allocate a database 'Connection' object
			conn = DriverManager.getConnection(url, username, password);  
			// Step 2: Allocate a 'Statement' object in the Connection
			stmt = conn.createStatement();		
			//ExecutionLog.log("============Connected with DataBase.============");
		} catch(Exception e) {
			System.err.println("============Could not Connect Execute the query due to Exception.============");
			e.printStackTrace();
			disconnectDB();
			throw e;
		}
	}

		
	/**
	 * @author Rajuddin:
	 * INSERT and DELETE statement
	 * @param query
	 * @throws SQLException
	 */
	public static void executeInsertDeleteQuery(String query) throws SQLException {
		connectDB() ;
		try {		
			ExecutionLog.log(query);
			stmt.executeUpdate(query);  
			disconnectDB();
		} catch(Exception e) {
			System.err.println("============Could not Connect Execute the query due to some error.============");
			e.printStackTrace();
			disconnectDB();
			throw e;			
		}		
	}


	/**
	 * @author Rajuddin:
	 * Retrieving the data from the DataBase
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static List<String> executeQuery(String query) throws SQLException {
		connectDB() ;
		List<String> data = new ArrayList<String>();
		ExecutionLog.log("Started executing Queries");
		try {		
			ExecutionLog.log("The SQL query is: " + query);
			ResultSet rs=stmt.executeQuery(query);
			ResultSetMetaData rsMetaData = rs.getMetaData();
			while(rs.next())  {// Move the cursor to the next row				 
				for (int colNo =1; colNo<=rsMetaData.getColumnCount(); colNo++)  {
					data.add(rs.getString(colNo));
				}
			}
			disconnectDB();				
		} catch(Exception e) {
			System.err.println("============Could not Connect Execute the query due to some error.============");
			e.printStackTrace();
			disconnectDB();
			throw e;			
		}		
		ExecutionLog.log("The Data retruned on executing the query: " + query);
		ExecutionLog.log(data+"");
		return data;
	}

	
	public static void disconnectDB() throws SQLException {
		//System.out.println("============Disconnecting the DB============");
		conn.close(); 
	}

	
	}
