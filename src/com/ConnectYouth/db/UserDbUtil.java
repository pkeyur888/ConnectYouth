package com.ConnectYouth.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ConnectYouth.Model.Message;
import com.ConnectYouth.Model.User;

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
					
	            }
			 
			 
				return founduser;
		} finally {
			close(conn,stm,res);
		}
			
		
	} 
	
	
	public ArrayList<User> selectAllUser() throws SQLException {
		ArrayList<User> userList= new ArrayList<>();
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		
		try {
			conn = this.dataSource.getConnection();
			String sql = String.format("SELECT FirstName,LastName,Email FROM user");
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			res = pstmt.executeQuery();
		
				while(res.next()){
					userList.add(new User(res.getString("FirstName"),res.getString("LastName"),res.getString("Email")));
				} 
				
				return userList;
		}finally {
				close(conn,stm,res);
			}
				
        }
	
	
	public ArrayList<User> friendList(String email) throws SQLException {
		ArrayList<User> friendList= new ArrayList<>();
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		
		try {
			conn = this.dataSource.getConnection();
			String sql = String.format("SELECT * FROM friends where RelatingUserEmail=?");
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
				while(res.next()){
					friendList.add(new User(res.getString("RelatedUserEmail"),res.getString("status")));
				} 
				
				return friendList;
		}finally {
				close(conn,stm,res);
			}
				
        }
	
			
		

	public boolean addFriend(User user) throws SQLException {
		
		
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		int execute;

		try {
			conn = this.dataSource.getConnection();
			 String sql=String.format("INSERT INTO friends  VALUES ('%s', '%s', '%s')",(user.getEmail()).trim(),(user.getRequestReciverID()).trim(),user.getFriendStatus());
			 stm= conn.createStatement();
			 execute=stm.executeUpdate(sql); 
			
			 
		} finally {
			close(conn,stm,res);
		}
			
		return execute==1?true:false;
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




	public int findRequest(String email) throws SQLException {
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		int requestCount=0;
		
		try {
			conn = this.dataSource.getConnection();
			String sql = String.format("SELECT * FROM friends where RelatedUserEmail=? and status=?");
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,email);
			pstmt.setString(2, "0");
			res = pstmt.executeQuery();
				while(res.next()){
					requestCount++;
				} 
				
				return requestCount;
		}finally {
				close(conn,stm,res);
			}
			
		
	}




	public ArrayList<User> findRequestList(User user) throws SQLException {
//		ArrayList<User> requestList=new ArrayList<>();
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		ArrayList<User> findRequestList=new ArrayList<>();


		try {
			conn = this.dataSource.getConnection();
			String sql = String.format("SELECT * FROM connectyouth.friends as f inner join connectyouth.user as u on u.Email= f.RelatingUserEmail where   f.status=? and f.RelatedUserEmail=?");
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, "0");
			pstmt.setString(2, user.getEmail().trim());
			res = pstmt.executeQuery();
				while(res.next()){
					findRequestList.add((new User(res.getString("FirstName"),res.getString("LastName"),res.getString("Email"))));
					
				} 
				return findRequestList; 
		}finally {
				close(conn,stm,res);
			}
		
	}




	public void acceptRequest(String senderEmail,String reciverEmail) throws SQLException {

		
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		

		try {
			conn = this.dataSource.getConnection();
			 String sql=String.format("update  friends set status=1 where RelatedUserEmail=? and RelatingUserEmail=? ");
			 PreparedStatement pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1,  reciverEmail.trim());
				pstmt.setString(2,senderEmail.trim());
				pstmt.executeUpdate();
				
				
			
			 
		} finally {
			close(conn,stm,res);
		}
			
		
		
	}




	public void deleteRequest(String userEmail) throws SQLException {

		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		

		try {
			conn = this.dataSource.getConnection();
			 String sql=String.format("DELETE FROM friends WHERE RelatingUserEmail=? ");
			 PreparedStatement pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1, userEmail);
				 pstmt.executeUpdate();
			
			 
		} finally {
			close(conn,stm,res);
		}
			
		
	}




public ArrayList<User> selectUserFriend(String email) throws SQLException {
	ArrayList<User> userList= new ArrayList<>();
	Connection conn=null ;
	Statement stm = null;
	ResultSet res = null;
	
	try {
			conn = this.dataSource.getConnection();
			String sql = String.format("select * from connectyouth.user where Email IN (select friends.RelatedUserEmail as friendemail from connectyouth.friends where status='1' and friends.RelatingUserEmail = ? union  select  friends.RelatingUserEmail as friendemail from connectyouth.friends where status='1' and friends.RelatedUserEmail=?)" );
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, email.trim());
			pstmt.setString(2, email.trim());
			
			res = pstmt.executeQuery();
			while(res.next()){
				userList.add(new User(res.getString("FirstName"),res.getString("LastName"),res.getString("Email")));	
            }
		 
			return userList;
		 
	} finally {
		close(conn,stm,res);
	}
}




public ArrayList<Message> selectUserMsg(String email, String reciverMsgEmail) throws SQLException {
	ArrayList<Message> messageList= new ArrayList<>();
	Connection conn=null ;
	Statement stm = null;
	ResultSet res = null;
	
	try {
		conn = this.dataSource.getConnection();
		
		
		String sql = String.format("select * from connectyouth.message where toUser=? and fromUser=? union select * from connectyouth.message where toUser=? and fromUser=?");
		PreparedStatement pstmt = conn.prepareStatement(sql); 
		pstmt.setString(1, email.trim());
		pstmt.setString(2, reciverMsgEmail.trim());
		pstmt.setString(3, reciverMsgEmail.trim());
		pstmt.setString(4, email.trim());
		
		
		res = pstmt.executeQuery();
		
		while(res.next()){
			messageList.add(new Message(res.getString("fromUser"),res.getString("toUser"),res.getString("message"),res.getString("date"),res.getInt("seen")));	
        }
		for(Message m:messageList) {
			System.out.println(m.getMessage());
		}
		
	return messageList;	
	 
} finally {
	close(conn,stm,res);
}
	
}


}