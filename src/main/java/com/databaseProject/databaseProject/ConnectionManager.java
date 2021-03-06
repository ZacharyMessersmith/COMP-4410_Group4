package com.databaseProject.databaseProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/dbproject";
	static final String	USERNAME = "root";
	static final String PASSWORD = "toor";

	ConnectionManager()
	{

	}
	
	public static	Connection	getConnection() throws SQLException
	{
	try
		{
		Class.forName( JDBC_DRIVER );
		return DriverManager.getConnection( DATABASE_URL, USERNAME, PASSWORD);
		}
	catch (ClassNotFoundException e)
		{
		System.out.println("Unable to locate call for JDBC Driver.");
		e.printStackTrace();
		return null;
		}
	}
	
}