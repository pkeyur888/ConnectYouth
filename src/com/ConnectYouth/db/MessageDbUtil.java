
package com.ConnectYouth.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ConnectYouth.Model.Message;

public class MessageDbUtil {
private DataSource dataSource;
	
	
	public MessageDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	

public ArrayList<Message> selectUserMsg(String email, String reciverMsgEmail) throws SQLException {
	ArrayList<Message> messageList= new ArrayList<>();
	Connection conn=null ;
	Statement stm = null;
	ResultSet res = null;
	
	try {
		conn = this.dataSource.getConnection();
		
		
		String sql = String.format("select * from connectyouth.message where toUser=? and fromUser=? union select * from connectyouth.message where toUser=? and fromUser=? order by date");
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, email.trim());
		pstmt.setString(2, reciverMsgEmail.trim());
		pstmt.setString(3, reciverMsgEmail.trim());
		pstmt.setString(4, email.trim());
		
		
		res = pstmt.executeQuery();
		
		while(res.next()){
			
			if(res.getString("fromUser")=="pkeyur888@gmail.com")
			{
				System.out.println(res.getString("message"));
			}
			messageList.add(new Message(res.getString("fromUser").trim(),res.getString("toUser").trim(),res.getString("message"),res.getString("date"),res.getInt("seen")));	
        }
		
	return messageList;	
	 
} finally {
	close(conn,stm,res);
}
	
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



	public void sendMsg(Message tempMsg) throws SQLException {
		
		
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		
		try {
			conn = this.dataSource.getConnection();
			 String sql=String.format("INSERT INTO message  VALUES ('%s', '%s', '%s','%s','%s')",tempMsg.getFromUser().trim(),tempMsg.getToUser().trim(),tempMsg.getMessage(),tempMsg.getDate(),tempMsg.getSeen());
			 stm= conn.createStatement();
			 stm.executeUpdate(sql); 
			
			 
		} finally {
			close(conn,stm,res);
		}
			
		
	}	
	   
	   
     }
	


		
	

