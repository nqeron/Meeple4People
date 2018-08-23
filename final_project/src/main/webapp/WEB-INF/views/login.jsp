<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - Login</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/login.css">
	
	<script type="text/javascript" src="/resources/js/login.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="pageTitle">
	Login
</div>

<br/>
<br/>
<div class="loginForm">
	<form name="login" action="/loginAction" method="post" onsubmit="return validateForm()">
	  <div class="form-group row">
	    <label for="username" class="col-sm-2 col-form-label">Username</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" id="username" name="username" value="">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
	    <div class="col-sm-6">
	      <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="">
	    </div>
	  </div>
	  <div class="loginButton">
	  	<button type="submit" class="btn btn-primary">Log In</button>
	  </div>
	</form>
	<div class="error">
		${error}
	</div>
</div>


</body>
</html>