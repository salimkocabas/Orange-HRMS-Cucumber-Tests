package com.hrms.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.hrms.steps.AddEmployeeSteps;
import com.hrms.utils.ConfigsReader;

import cucumber.api.java.en.When;

public class JDBCDemo {
	String dbUsername = "syntax_hrm";
	String dbPassword = "syntaxhrm123";
	// jdbc:type driver:host:port/name of the database
	String dbUrl = "jdbc:mysql://18.232.148.34:3306/syntaxhrm_mysql";

	

	//@Test
	public void getDataFromDatabase() throws SQLException {
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		System.out.println("Connection is created");
		Statement st = connection.createStatement();
		ResultSet rset = st.executeQuery("select * from ohrm_nationality");
		rset.next();
		String firstRowData = rset.getString("name");
		System.out.println(firstRowData);
		rset.next();
		String secondRowData = rset.getObject("name").toString();
		System.out.println(secondRowData);
		String data;
		while (rset.next()) {
			data = rset.getObject("name").toString();
			System.out.println(data);
		}
		rset.close();
		st.close();
		connection.close();
	}
	
	@Test
	public void dbtest() throws SQLException{
		
		
		    ConfigsReader.readProperties("src/test/resources/configs/Configuration.properties");
			
			String dbUser = ConfigsReader.getProperty("dbUsername");
			String dbPass=ConfigsReader.getProperty("dbPassword");
			String urlofDb=  ConfigsReader.getProperty("dbUrl");

			Connection connection = DriverManager.getConnection(urlofDb, dbUser, dbPass);
			System.out.println("Connection is created");
		    Statement st = connection.createStatement();
			String sqlQuery=" select emp_firstname from hs_hr_employees";	
			ResultSet rset = st.executeQuery(sqlQuery );
			String data;
			while (rset.next()) {
				data = rset.getObject("emp_firstname").toString();
				System.out.println(data);
			}
			
			rset.close();
			st.close();
			connection.close();
			
//			System.out.println("ONE NAME FROM DB=" +onename);
//			System.out.println("DB TEST EXECUTION FINISHED");
		
	}
	
	}
	

