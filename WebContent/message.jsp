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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <script defer src="script.js"></script>
<title>Message</title>
</head>
<body>
 <h2 class="header">Message </h2>

<div class="header">
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
             <span class="item-count"><%= temp.getUserRequestList().size() %></span>
     </form>
        <input type="button" class='fas fa-bell' style='font-size:40px;color:red' name="friends" value="&#xf0f3">   
    </div>
    <div class="loginUser">
        <h2><%= temp.getFname()%></h2>
        <form method="post" action="login">
			<input type="submit" name="logout" value="logout">
		</form>
         </div>
  </div>       
      <div class="msg">
            <h3>Messages</h3>
          
             <tag:forEach var="user" items="${userList}">
              <form method="post" action="message">
             	<input type="hidden" value="${user.getEmail()}" name="reciverMsg">
            	<input type="submit" value="${user.getFname()}" >
              </form>	
           	</tag:forEach>
           	
        </div>    
        <div class="msgBox">
<h3>Keyur</h3>
<tag:forEach var="message" items="${messageList}">
<p>${message.getMessage()} </p>
</tag:forEach>

<input type="text" name="writeMsg" placeholder="Type Somthing">
<input type="submit" value="Send">

</div>

</body>
</html>