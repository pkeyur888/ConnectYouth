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
<title>Connect Youth</title>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
th, td {
  padding: 5px;
  text-align: left;
}
</style>
</head>
<body>
 <h2 class="header">Friend Request</h2>

<div class="header">
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
         <div class="createPost">
            <input type="text" name="post" placeholder="Search Friends">
            <input type="submit" value="Search">
        </div>


</div>
<table style="width:50%">
  <caption>Friend Request</caption>
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email</th>
     <th>Action</th>
  </tr>

 

 
  
  <%
    
	//User temp = (User) session.getAttribute("user");
	
	for(User u:temp.getUserRequestList()) {
		out.println("<tr>");
		out.println("<td>" + u.getFname() +"</td>" );
		out.println("<td>" + u.getLname() +"</td>" );
		out.println("<td>" + u.getEmail() +"</td>" );
		out.println("<td><form method='post' action='friendRequest'>" );
		out.println("<input type='hidden' name='userEmail' value='"+u.getEmail()+"'>");
		out.println("<input type='submit' name='Accept' value='Accept'>");
		out.println("<input type='submit' name='Delete' value='Delete'>");
		out.println(" </form></td>");
		out.println("</tr>");
	}
	
	
    	
    %>
    
    
   
  

</table>
</body>
</html>