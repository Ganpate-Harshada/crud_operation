<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

 <link rel="stylesheet" 
 href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" 
 integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" 
 crossorigin="anonymous">
 
 <link href='https://fonts.googleapis.com/css?family=Aclonica' rel='stylesheet'>
 
 
 <style>
 body{
 background-image:url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBXNMNjCeUtWovKssv1lEPlyOxlguwRLQ6Cw&usqp=CAU");
 }
 h1{
 font-family: 'Aclonica';
 font-size:90px;
 }
 .container{
 font-size:30px;
 } 
 
 
 </style>
 
</head>
<body>

<header>

<nav class="navbar  navbar-expand-md navber-dark"
  style="background-color:skyblue ">
  <div>
  <a class="navbar-brand">User Management</a>
</div>

<ul class="navbar-nav">
<li><a href ="<%=request.getContextPath() %>/list"
class="nav-link">Users</a></li>
</ul>
</nav>
</header>
<br>

<div class="row">
<div class="container  " >
<h1 class="text-center">List of Users</h1>
<hr>

<div class="container1 text-left ">
<a href="<%=request.getContextPath() %>/new" class="btn btn-default
">Add New User</a>
</div>
<br>

<table class="table table-bordered">
<thead>
<tr>
<th>ID</th>
<th>NAME</th>
<th>EMAIL</th>
<th>COUNTRY</th>
<th>Actions</th>
</tr>
</thead>


<tbody>

<c:forEach var="user" items="${listUser}">

<tr>
<td><c:out value="${user.id}"/></td>
<td><c:out value="${user.name}"/></td>
<td><c:out value="${user.email}"/></td>
<td><c:out value="${user.country}"/></td>
<td><a href="edit?id=<c:out value='${user.id}'/>">Edit</a>
     &nbsp; &nbsp; &nbsp; &nbsp;
    <a href="delete?id=<c:out value='${user.id}'/>">Delete</a>
</td>
</tr>
</c:forEach>
</tbody>


</table>
</div>

</div>

</body>
</html>