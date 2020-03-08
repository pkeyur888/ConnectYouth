package com.ConnectYouth.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import com.ConnectYouth.db.UserDbUtil;

public class User {

	public ArrayList<Post> postList=new ArrayList<>();
	public ArrayList<User> userList=new ArrayList<>();
	
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
	
	private String requestReciverID;
	public String getRequestReciverID() {
		return this.requestReciverID;
	}
	public void setRequestReciverID(String requestReciverID) {
		this.requestReciverID=requestReciverID;
	}
	
	private String friendStatus;
	public String getFriendStatus() {
		return this.friendStatus;
	}
	public void setFriendStatus(String friendStatus) {
		this.friendStatus=friendStatus;
	}
	
	public User() {
		fname="";
		lname="";
		email="";
		password="";
		requestReciverID="";
	}
	public User(String requestReciverID,String friendStatus) {
		this.requestReciverID=requestReciverID;
		this.friendStatus=friendStatus;
	}
	
		public User(String fname, String lname, String email) {
			this.email=email;
			this.fname=fname;
			this.lname=lname;
			
			System.out.println(email);
			System.out.println(fname);
			
			
			
			
	}

		
	
	
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
	
	public boolean addFriend(UserDbUtil userdb) {
		
		try {
			if(userdb.addFriend(this))
			{
				return true;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
