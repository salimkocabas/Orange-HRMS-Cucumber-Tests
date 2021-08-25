package com.hrms.steps;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.ConfigsReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;


public class DbSteps extends CommonMethods{
	List<Map<String,String>> listtablefromdb;
	
	Connection connection;
	

	@When("I retrive data table from DataBase")
	public void i_retrive_data_table_from_DataBase() throws SQLException {
		
		String dbUser = ConfigsReader.getProperty("dbUsername");
		String dbPass=ConfigsReader.getProperty("dbPassword");
		String urlofDb=  ConfigsReader.getProperty("dbUrl");

		connection = DriverManager.getConnection(urlofDb, dbUser, dbPass);
			
	
		System.out.println("Connection is created");
		System.out.println("********EMPLOYEE ID = "+AddEmployeeSteps.empidnumber);
		Statement st = connection.createStatement();
		String sqlQuery=" select emp_firstname"// emp_middle_name, emp_lastname"
						+ " from hs_hr_employees "
						+ " where emp_number =6676";
		

				
		ResultSet rset = st.executeQuery(sqlQuery ); //+ AddEmployeeSteps.empidnumber
		
		ResultSetMetaData rsetmeta= rset.getMetaData();
		int colnumber= rsetmeta.getColumnCount();
		while(rset.next()) {
		String onename= rset.getObject(0).toString();
		
		System.out.println("ONE NAME FROM DB=" +onename);
		}
		/*
		listtablefromdb=new ArrayList<>();
		while(rset.next()) {
			
			Map<String,String> map=new LinkedHashMap<>();
			
			for (int i=1; i<colnumber; i++ ) {
				
				map.put(rsetmeta.getColumnName(i), rset.getObject(i).toString());
			}
			
			listtablefromdb.add(map);
		}
		System.out.println("*******TABLE FROM DATABASE = "+ listtablefromdb);
		*/
	
		rset.close();
		st.close();
		connection.close();
	}

	@When("I retrive data table from my scenario")
	public void i_retrive_data_table_from_my_scenario(DataTable datatable) {
		
		List<Map<String,String>> tablefromscenario=datatable.asMaps();
		System.out.println("*******TABLE FROM SCENARIO = "+ tablefromscenario);
		
		assertTrue("TABLES ARE MATCHED", tablefromscenario.equals(listtablefromdb));
		
	   
	}

	@Then("I verify both tables are equals")
	public void i_verify_both_tables_are_equals() {
	   
	}
}
