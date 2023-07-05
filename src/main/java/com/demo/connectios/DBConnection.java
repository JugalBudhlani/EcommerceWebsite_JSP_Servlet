package com.demo.connectios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static  Connection connection=null;
	
	public static Connection getConnection() {
		
		if(connection==null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url="jdbc:mysql://localhost:3306/ecom";
				String  user="root";
				String pass="Jugal13";
				
				connection= DriverManager.getConnection(url, user, pass);
				
				System.out.print("Connection Succesfully Done");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		return connection;
		
	}
}
