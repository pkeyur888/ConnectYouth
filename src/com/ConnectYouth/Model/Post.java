package com.ConnectYouth.Model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ConnectYouth.db.UserDbUtil;

public class Post {

	public Post(String postID, String content, String image, String date) {
		this.postID=postID;
		this.content=content;
		this.image=image;
		this.date=date;	
	}
	
	
	public Post() {
		postID="";
		content="";
		image="";
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
	
	
	
	private String date;
	
	public String getDate() {
		return this.date;
	}
	
	public void setDate(String date) {
		 this.date=date;
	}
	
	
	
	
	
}
