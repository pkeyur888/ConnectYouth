package com.ConnectYouth.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.ConnectYouth.Model.User;

import java.sql.Statement;

public class UserDbUtil {
	
	private DataSource dataSource;
	
	public UserDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
		
	}
	
	


	public boolean  signup(User user) throws SQLException {
		
	Connection conn=null ;
	Statement stm = null;
	ResultSet res = null;
	int execute;
	
	try {
		conn = this.dataSource.getConnection();
		 String sql=String.format("INSERT INTO user VALUES('%s','%s','%s','%s')",user.getFname(),user.getLname(),user.getEmail(),user.getPassword());
		 stm= conn.createStatement();
		 execute=stm.executeUpdate(sql); 
		
	} finally {
		close(conn,stm,res);
	}
		return execute==1?true:false;
	}
	
	
	public User login(User user) throws SQLException{
		User founduser = new User();
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		
		try {
				conn = this.dataSource.getConnection();
				String sql = String.format("SELECT * FROM user WHERE Email=?");
				PreparedStatement pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1, user.getEmail());
				res = pstmt.executeQuery();
				while(res.next()){
					founduser.setFname(res.getString("FirstName"));
					founduser.setLname(res.getString("LastName"));
					founduser.setPassword(res.getString("Password"));
					founduser.setEmail(res.getString("Email"));
					
					System.out.println("aaaaaa");
					System.out.println(founduser.getPassword());
					System.out.println(founduser.getEmail());
	            }
			 
			 
			 
		} finally {
			close(conn,stm,res);
		}
			return founduser;
		
		
		
		
	} 
	
	
	private void close(Connection conn,Statement smt,ResultSet res) {
		try {
			if(res != null) {
				res.close();
			}
			if(smt != null) {
				smt.close();
			}
			if(conn != null ) {
				conn.close();
			}
		}
		catch(Exception exe) {
			exe.printStackTrace();
		}
	}

}
