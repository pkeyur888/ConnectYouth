package com.ConnectYouth.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import com.ConnectYouth.db.UserDbUtil;

public class User {

	
	private String fname;
	
	public String getFname() {
		return this.fname;
	}
	
	public void setFname(String fname) {
		 this.fname=fname;
	}
	
	private String lname;
	
	public String getLname() {
		return this.lname;
	}
	
	public void setLname(String lname) {
		 this.lname=lname;
	}
	
	private String email;
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		 this.email=email;
	}
	
	
	private String password;
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		 this.password=password;
	}
	
	
	public User() {
		fname="";
		lname="";
		email="";
		password="";
	}
	
		public ArrayList<Post> postList=new ArrayList<>();
	
	
	public boolean Register(UserDbUtil userdb) {
		
		try {
			if(userdb.signup(this))
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean Login(UserDbUtil userdb) {
		User founduser=new User();
		System.out.println("in user");
		
		try {
			founduser=userdb.login(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(founduser!=null && this.getPassword().equals(founduser.getPassword()))
		{		
			return true;
		}
		
		return false;
		
	
	}
	
}
