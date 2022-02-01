package com.com.comcast.orgTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class SelectQuery {

	public static void main(String[] args) throws SQLException {
		Connection conn=null;
		try {
			Driver driverRef=new Driver();

			//step 1  register Mysql db
			DriverManager.registerDriver(driverRef);

			//Step 2  connect to mysql
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			System.out.println("Db Connection done");
			//step 3 create query statement
			Statement state=conn.createStatement();
			String query="select * from project";

			//step 4 execute  query
			ResultSet resultSet = state.executeQuery(query);

			//step 5 use itterator for select set of result
			while(resultSet.next())
			{
				System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+
						resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"
						+resultSet.getString(5)+"\t"+resultSet.getString(6));
			}
		}
		catch(Exception e)
		{

		}finally {
			//close connection
			conn.close();
			System.out.println("db connection close");
		}

	}

}
