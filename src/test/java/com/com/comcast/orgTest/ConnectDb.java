package com.com.comcast.orgTest;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ConnectDb {

	public static void main(String[] args) throws SQLException {
		//driver from com.mysql.jdbc.Driver
		Connection conn=null;
		try {
		Driver driverRef=new Driver();
		
		//Load driver
		DriverManager.registerDriver(driverRef);
		
		//db connection
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		System.out.println("Connection is done");
		//create query statement
		Statement state=conn.createStatement();
		String query="select * from project";
		
		
		//excecte query
		ResultSet resultSet = state.executeQuery(query);
		
		//using iterator
		while(resultSet.next())
		{
			System.out.println(resultSet.getString(1)+"\t"+
		resultSet.getString(2)+ "\t"+ resultSet.getString(3)+ "\t"+ resultSet.getString(4));
		}
		}
		catch(Exception e)
		{
			
		}finally {
			//close connection
			conn.close();
			System.out.println("Close Db Connection");
		}
	}

}
