<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
<table style="width:50%">
  <caption>Friend Request</caption>
  <tr>
    <th>Name</th>
    <th>Action</th>
    <th>Email</th>
  </tr>
  <tag:forEach var="request" items="${requestList}">
  ${requestList }
  <tr>
  <td>${request.getFname()}</td>
  <td>${request.getLname()} </td>
  <td>${request.getEmail()} </td>
 
  </tr>
  </tag:forEach>
</table>
</body>
</html>