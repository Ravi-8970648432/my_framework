package com.com.comcast.orgTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class NonSelectQuery {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		int result = 0;
		try {
			Driver driverRef=new Driver();

			//step 1 register/load mysql db
			DriverManager.registerDriver(driverRef);

			//step 2 connection mysql
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			System.out.println("Connection is done");

			//step 3 create query statement
			Statement state = conn.createStatement();

			//insert query
			//String query="insert into project values('TY_PROJ_005', 'Varun', '12/01/2022',"
					//+ " 'Automobile', 'Created', 25)";

			//update query
			String query="update project SET  project_name='E-Medical', status='Created',team_size=15"
			+ " where project_id='TY_PROJ_002'";

			//delete one by one query
			//String query="delete from project where created_by='Ravi'";

			//delete all data

			//String query="delete from project";

			//step 4 execute query
			result = state.executeUpdate(query);
		}catch(Exception e)
		{

		}finally {
			// step 5 close connection
			if(result==1)
			{
				System.out.println("Project Insert Succesfully");
			}
			else
			{
				System.out.println("Project not inserted");
			}
			conn.close();
			System.out.println("close db connection");
		}


	}

}
