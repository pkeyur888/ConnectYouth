package com.ConnectYouth.Model;

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
		return date;
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
