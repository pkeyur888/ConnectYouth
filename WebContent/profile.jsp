<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tag" %>
     <%@page import="com.ConnectYouth.Model.*" %>
      <%
		User temp = (User) session.getAttribute("user");
      %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <title>Profile</title>
<style>
.w3-card-4{
	margin-top:20px;
}
</style>
</head>
<h2 class="header">Profile</h2>


    <div class="navbar">
    <form action="home" method="post"> 
        <input type="submit" style='font-size:40px;color:red' class='fa fa-home' name="home" value="&#xf015">
     </form> 
     <form action="profile" method="post">    
        <input type="submit" style='font-size:40px;color:red' class='fas fa-user-circle' name="profile" value="&#xf2bd">
     </form>    
        <input type="button" class='fas fa-comment-dots' style='font-size:40px;color:red' name="massege" value="&#xf4ad"
            onclick=msg()>
     <form action="friendRequest" method="post"> 
        <input type="submit" class='fas fa-user-friends' style='font-size:40px;color:red' name="friendRequest"
            value="&#xf500">
             <span class="item-count"><tag:out value="<%= temp.getUserRequestList().size() %>"></tag:out></span>
     </form>
        <input type="button" class='fas fa-bell' style='font-size:40px;color:red' name="friends" value="&#xf0f3">   
    </div>
    <div class="loginUser">
        <h2><%= temp.getFname()%></h2>
        <form method="post" action="login">
			<input type="submit" name="logout" value="logout">
		</form>
         </div>
         <div class="createPost">
            <input type="text" name="post" placeholder="What is in your mind">
            <input type="submit" value="Post">
        </div>
    <div class="posts">
    
    
    <tag:forEach var="post" items="${postList}">
    
        <div  class="postContent">
<p>${post.getContent()}</p>
<input type="submit" value="Like">
<input type="submit" value="Save">
</div>
</tag:forEach>
    </div>
<div class="frindList">
    <h3>Friends</h3>
    
     <tag:forEach var="user" items="${userList}">
    <h2>${user.getFname()} ${user.getLname()}</h2>
    </tag:forEach>

</div>
<a href="home">Home</a>

</html>