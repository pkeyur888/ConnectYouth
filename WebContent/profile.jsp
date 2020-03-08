<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="tag" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ConnectYouth</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.w3-card-4{
	margin-top:20px;
}
</style>
</head>
<form method="post" action="login">
		
		<input type="submit" name="logout" value="logout">
	</form>
<h1>Hello</h1>

<tag:forEach var="post" items="${postList}">

<div class="w3-card-4" style="width:50%">
    <img src="img_snowtops.jpg" alt="Alps" style="width:100%">
    <div class="w3-container w3-center">
      <p>${post.getContent()}</p>
    </div>
  </div>
</tag:forEach>

<a href="home">Home</a>

</html>