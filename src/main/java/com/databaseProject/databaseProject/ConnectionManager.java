package com.databaseProject.databaseProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://awestfall8:3306/comp4410assignments";
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