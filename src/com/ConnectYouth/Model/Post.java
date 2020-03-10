package com.ConnectYouth.Model;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ConnectYouth.db.UserDbUtil;

public class Post {

	public Post(String postID, String content,  String date) {
		this.postID=postID;
		this.content=content;
		
		this.date=date;	
	}
	
public Post(String content,String email) {
		
		this.content=content;
		this.email = email;
		
	}

private String date;
public String getDate() {
	
	return java.time.LocalDate.now().toString();
}



public String getID() {
	SecureRandom rand = new SecureRandom();
	int num = rand.nextInt(1000000);
	String postID= String.format("0%5d", num);
	return postID;
}
	
	public Post() {
		postID="";
		content="";
		
		date="";
		email="";
	}


	private String postID;
	

	public String getPostID() {
		return this.postID;
	}
	
	public void setPostID(String postID) {
		 this.postID=postID;
	}
	
	private String email;
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		 this.email=email;
	}
	
	
	private String content;
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		 this.content=content;
	}
	
	
	
	private String image;
	
	public String getImage() {
		return this.image;
	}
	
	public void setImage(String image) {
		 this.image=image;
	}
	
	
	

	
	
	
	
	
}
