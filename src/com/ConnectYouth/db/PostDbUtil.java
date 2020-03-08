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
	
	
	public ArrayList selectUserPost(String email) throws SQLException {
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
					postList.add(new Post(res.getString("postID"),res.getString("content"),res.getString("image"),res.getString("date")));	
	            }
			 
				return postList;
			 
		} finally {
			close(conn,stm,res);
		}

}


	public ArrayList selectAllPost() throws SQLException {
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
					postList.add(new Post(res.getString("postID"),res.getString("content"),res.getString("image"),res.getString("date")));
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

}
