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

</head>
<body>
<header>

<nav class="navbar  navbar-expand-md navber-dark"
  style="background-color:blue">
  <div>
  <a class="navbar-brand" href="https://www.xadmin.net">User Management Application</a>
</div>

<ul class="navbar-nav">
<li><a href ="<%=request.getContextPath() %>/list"
class="nav-link">Users</a></li>
</ul>
</nav>
</header>
<br>

<div class ="container col-md-5">
<div class ="card">
<div class= "card-body">
<c:if test="${user != null}">
<form action="update" method="post">
</c:if>
<c:if test="${user == null}">
<form action="insert" method="post">
</c:if>

<caption>
<h2>
<c:if test="${user != null}">
Edit User
</c:if>
<c:if test="${user == null}">
Add New User
</c:if>
</h2>

</caption>

<c:if test="${user == null}">
<input type="hidden" name="id" value="<c:out value='${user.std_id}'/>" required="required"/>
</c:if>

<fieldset class="form-group">
<label>User Name</label><input type="text" value="<c:out value='${user.std_name}'/>" class="form-control"
name="name" >
</fieldset>

<fieldset class="form-group">
<label>User Email</label><input type="text" value="<c:out value='${user.std_email}'/>" class="form-control"
name="email">
</fieldset>

<fieldset class="form-group">
<lablel>User Country</lablel><input type="text" value="<c:out value='${user.std_country}'/>" class="form-control"
name="country">
</fieldset>

<button type="submit" class="btn btn-success">Save</button>
</form>
</div>
</div>
</div>
</body>
</html>