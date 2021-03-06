package com.ConnectYouth.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ConnectYouth.Model.Post;

public class PostDbUtil {
	private DataSource dataSource;
	
	
	public PostDbUtil(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	
	public ArrayList<Post> selectUserPost(String email) throws SQLException {
		ArrayList<Post> postList= new ArrayList<>();
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		
		try {
				conn = this.dataSource.getConnection();
				String sql = String.format("SELECT * FROM posts WHERE email=?");
				PreparedStatement pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1,email);
				res = pstmt.executeQuery();
				while(res.next()){
					postList.add(new Post(res.getString("postID"),res.getString("content"),res.getString("date")));	
	            }
			 
				return postList;
			 
		} finally {
			close(conn,stm,res);
		}

}
	

	public ArrayList<Post> selectAllPost() throws SQLException {
		ArrayList<Post> postList= new ArrayList<>();
		Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		try {
			conn = this.dataSource.getConnection();
			String sql = String.format("SELECT * FROM posts");
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			res = pstmt.executeQuery();
		
				while(res.next()){
					postList.add(new Post(res.getString("postID"),res.getString("content"),res.getString("date")));
				} 
				
				return postList;
		}finally {
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


public int CreatePost(Post tempPost) throws SQLException {
	
		
	    Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		int status = 0;
	    try {
	    	
	    	conn = this.dataSource.getConnection();
	    	
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO posts VALUES(?,?,?,?)");
			pstmt.setString(1,tempPost.getID().trim());
			pstmt.setString(2,tempPost.getEmail().trim());
			pstmt.setString(3,tempPost.getContent().trim());
			pstmt.setString(4,tempPost.getDate());

			
			status=pstmt.executeUpdate();

			System.out.println("one");
			 
			System.out.println("one1");
			 
	    }
	  
	    
	    finally {
			close(conn,stm,res);
		}
	    return status;
     }
	

public void deletePost(String postId) throws SQLException {
	
	 Connection conn=null ;
		Statement stm = null;
		ResultSet res = null;
		
		
		
		try {
			conn = this.dataSource.getConnection();
			 String sql=String.format("DELETE FROM posts WHERE postID=? ");
			 PreparedStatement pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1, postId);
				 pstmt.executeUpdate();
				 System.out.println(postId);
			 
		} finally {
			close(conn,stm,res);
		}
			
}


public void editPost(String postId, String postContent) throws SQLException {
	 Connection conn=null ;
		Statement stm = null;
		ResultSet res = null; 
		
		
		
		try {
			conn = this.dataSource.getConnection();
			 String sql=String.format("UPDATE posts   SET content= ? WHERE postID=?  ");
			
			 PreparedStatement pstmt = conn.prepareStatement(sql); 
				pstmt.setString(1, postContent);
				pstmt.setString(2, postId);
				 pstmt.executeUpdate();
				 System.out.println(postId);
			 
		} finally {
			close(conn,stm,res);
		}
	
}

}
