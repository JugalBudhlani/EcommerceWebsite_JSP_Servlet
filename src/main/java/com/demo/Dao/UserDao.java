package com.demo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.model.Users;

public class UserDao {

	
	private Connection conn;
	
	private String qury;
	private PreparedStatement pst;
	private ResultSet rs;
	

	public UserDao(Connection conn) {
	
		this.conn = conn;
	}
	
	public Users userLogin(String email,String password) {
		
		Users user=null;
		
		try {
			qury="select * from users where  email=? and password=?";
			
			pst=this.conn.prepareStatement(qury);
			
			pst.setString(1,email);
			pst.setString(2,password);
			
			rs=pst.executeQuery();
			
			while(rs.next()) {
				
			 user =new Users();
			 user.setId(rs.getInt(1));
			 user.setName(rs.getString(2));
			 user.setEmail(rs.getString(3));
			}
			
		 	System.out.print("fffff");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return user;
	}
	
}
