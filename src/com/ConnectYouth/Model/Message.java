package com.ConnectYouth.Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ConnectYouth.db.MessageDbUtil;

public class Message {
	
	private String fromUser;
	private String toUser;
	private String message;
	private String date;
	private int seen;
	
	public Message(String fromUser, String toUser, String message, String date, int seen) {
		this.fromUser=fromUser;
		this.toUser=toUser;
		this.message=message;
		this.date=date;
		this.seen=seen;
		
	}
	public Message(String email, String reciverID, String msg,int seen) {
		this.fromUser=email;
		this.toUser=reciverID;
		this.message=msg;
		this.seen=seen;
		
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	
	
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public String getDate() {
		 
	       
	       SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	       Date date = new Date(System.currentTimeMillis());
	       return formatter.format(date);
		
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public int getSeen() {
		return seen;
	}
	public void setSeen(int seen) {
		this.seen = seen;
	}
	

}
