package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DataBaseConnectivity {

	public static void main(String[] args) {
		System.out.println(getMyConnection());
	}
	
	
	
	public static Connection getMyConnection()
	{
		ResourceBundle bundle = ResourceBundle.getBundle("com.model.db");
		String classname = bundle.getString("classname");
		String url = bundle.getString("url");
		String username = bundle.getString("username");
		String password = bundle.getString("password");
		
		
		Connection connection = null;
		
		try {
			Class.forName(classname);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return connection;
	}
	
	
	public static void closeAll(Connection connection,Statement statement ,ResultSet resultSet)
	{
	
		
			try {
				if(connection!=null)
				connection.close();
				if(statement!=null)
					statement.close();
				
				if(resultSet!=null)
					resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
}
