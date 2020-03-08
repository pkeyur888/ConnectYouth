<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tag" %>
    
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="ISO-8859-1">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <title>Home</title>
</head>
<body>
  <h2 class="header">Home</h2>


    <div class="navbar">
        <input type="button" style='font-size:40px;color:red' class='fa fa-home' name="profile" value="&#xf015">
        <input type="button" style='font-size:40px;color:red' class='fas fa-user-circle' name="profile" value="&#xf2bd">
        <input type="button" class='fas fa-comment-dots' style='font-size:40px;color:red' name="massege" value="&#xf4ad"
            onclick=msg()>
           <form action="friendRequest" method="post"> 
        <input type="submit" class='fas fa-user-friends' style='font-size:40px;color:red' name="friendRequest"
            value="&#xf500">
             <span class="item-count"><tag:out value="${requestCounter}"></tag:out></span>
             </form>
        <input type="button" class='fas fa-bell' style='font-size:40px;color:red' name="friends" value="&#xf0f3">   
    </div>
    <div class="loginUser">
        <h2>Samar Girn</h2>
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
    

<table style="width:50%">
  <tr>
    <th>First</th>
    <th>Last</th>
    <th>Email</th>
  </tr>
  
  <tag:forEach var="user" items="${userList}">
  
  <tr>
  <td>${user.getFname()}</td>
  <td>${user.getLname()} </td>
  <td>${user.getEmail()} </td>
 
  
  		
   <td><form method="post" action="addFriend">
   			<input type="hidden" name="friendEmail" value="${user.getEmail()} ">
   			<input type="submit" value="Add Friend" name="addfriend">
   </form></td>
 

  </tr>
  </tag:forEach>
  
</table>

</body>

</html>