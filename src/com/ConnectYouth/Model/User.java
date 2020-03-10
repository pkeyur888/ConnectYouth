package com.ConnectYouth.Model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ConnectYouth.db.PostDbUtil;
import com.ConnectYouth.db.UserDbUtil;

public class User {

	public ArrayList<Post> postList=new ArrayList<>();
	public ArrayList<User> userList=new ArrayList<>();
	public ArrayList<User> userRequestList=new ArrayList<>();
	
	public ArrayList<User> getUserRequestList() {
		return userRequestList;
	}

	public void setUserRequestList(ArrayList<User> userRequestList) {
		this.userRequestList = userRequestList;
	}

	public ArrayList<User> getUserList() {
		return this.userList;
	}
	
	public void setUserList(ArrayList<User> userList) {
		this.userList=userList;
	}

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
	
	public User Login(UserDbUtil userdb) {
						
		try {
			return userdb.login(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
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
	public void acceptRequest(UserDbUtil userdb,String userEmail) {
		try {
			userdb.acceptRequest(userEmail,this.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void deleteRequest(UserDbUtil userdb,String userEmail) {
		try {
			userdb.deleteRequest(userEmail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void CreatePost(String content, PostDbUtil postdb) {
		
		Post tempPost=new Post(content,this.email);
		try {
			postdb.CreatePost(tempPost);
		} catch (SQLException e) {
			// TODO Auto-generated catch block																						
			e.printStackTrace();
		
	}
	
}
}