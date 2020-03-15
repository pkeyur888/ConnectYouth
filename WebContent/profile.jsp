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
        <form action="message" method="post">   
        <input type="submit" class='fas fa-comment-dots' style='font-size:40px;color:red' name="massege" value="&#xf4ad"
            onclick=msg()>
       </form>
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
          	<form action="createpost" method="post">
            <input type="text" name="post" placeholder="What is in your mind">
            <input type="submit" name="createPost" value="Post">
            </form>
        </div>
    <div class="posts">
    
    
    <tag:forEach var="post" items="${postList}">
     <%! int i = 0;  %>
   <% i++; %>
        <div  class="postContent">
<p>${post.getContent()}</p>
<form action="oprationsPost" method="post" >
<input type="hidden" name="postId" value="${post.getPostID()}">
<input type="submit" value="Delete" name="delete">



<input type="button" value="Edit" onclick="showEdit(<%=i%>)">


<div class="editPost" id="edit<%=i%>">

    <textarea  rows="4" cols="40" placeholder="Edit Post" name="postContent"> ${post.getContent()}</textarea>
    
        <input type="hidden" name="postId" value="${post.getPostID()}">

        <input type="submit" value="Save" name="save"onclick="hideEdit(<%=i%>)">
</div>
</div>
</form>

</tag:forEach>
    </div>
<div class="frindList">
    <h3>Friends</h3>
    
     <tag:forEach var="user" items="${userList}">
    <h2>${user.getFname()} ${user.getLname()}</h2>
    </tag:forEach>

</div>
<script>
function showEdit(a)
{
    document.getElementById('edit'+ a ).style.display = "block";
}
function hideEdit(a){
    document.querySelector('#edit'+ a ).style.display = "none";

}
         </script>

</html>