package com.com.comcast.orgTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class UnitTesting {

	@Test
	public void unitTest() throws SQLException
	{
		String expectName="HealthCare";
		Connection conn=null;
		try {
			Driver driverRef = new Driver();

			//step1 register/load mysql db
			DriverManager.registerDriver(driverRef);

			//step 2 db connection
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			System.out.println("Db Connection done");
			//step 3 create query statement 
			Statement state = conn.createStatement();
			String query="select * from project";

			//step 4 execute query
			ResultSet resultSet = state.executeQuery(query);

			// step 5 read data from resultset using iterator
			boolean flag=false;
			while(resultSet.next())
			{
				String list_Of_Name=resultSet.getString(4);
				if(list_Of_Name.equals(expectName))
				{
					flag=true;
				}
			}
			Assert.assertTrue(flag);
		}
		catch(Exception e)
		{
			
		}finally {
			//Step 6 close db connection
			conn.close();
			System.out.println("close db connection");
		}
	}

}
