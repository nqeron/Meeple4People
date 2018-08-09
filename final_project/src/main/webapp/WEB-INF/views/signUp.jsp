<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People Sign Up</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/signUp.css">
	
	<script type="text/javascript" src="/resources/js/signUp.js"></script>
</head>
<body>
<div class = "header">
  <div class="main-dropdown">
    <button class="dropbtn"> <img style="height:45px" src="https://png.icons8.com/color/180/drag-list-down.png">
      <!--<i class="fas fa-bars fa-5x"></i>-->
    </button>
  
      <div class="dropdown-content">
        <a href="#">Home</a>
        <a href="#">Shopping Cart</a>
     	<a href="#">About</a>
     	<a href="#">FAQ</a>
     	<a href="#">Browse Games</a>
     	<a href="#">Rentals</a>
      </div>
    </div> 
  <div class = "searchBar"><div class="form-group">
          		<input type="text" class="form-control" placeholder="Search">
  </div></div>
  <div class = "userProfile-dropdown">
    
    <button class = "dropbtn"><!--<i class="fas fa-user-circle fa-5x"></i>--> <img style="height:45px" src = "https://png.icons8.com/cotton/40/000000/gender-neutral-user.png" /></button>
    <div class="dropdown-content">
    <c:choose>
    	<c:when test="${customer != null}">
    		<a href="#"> User Profile</a>
    		<a href="#"> Logout</a>
    	</c:when>
    	<c:otherwise>
    		<a href="#"> Login </a>
      		<a href="#"> Sign Up </a>
    	</c:otherwise>
    </c:choose>
    </div>
   </div>
  <div class = "shoppingCart"><!--<i class="fas fa-shopping-cart fa-5x"></i>--> <img style="height:45px" src="https://png.icons8.com/ios/40/000000/shopping-cart-filled.png" /></div>
</div>

<div class="pageTitle">Register</div>
<br/>
<br/>
<div class="registrationForm">
	<form name="registerForm" action="registerAction" method="post" onsubmit="return validateForm()">
	  <div class="form-group row">
	    <label for="username" class="col-sm-2 col-form-label">Username</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" id="username" name="username" value="">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="email" class="col-sm-2 col-form-label">Email</label>
	    <div class="col-sm-6">
	      <input type="text" class="form-control" id="email" name="email" value="">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
	    <div class="col-sm-6">
	      <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="confirmPass" class="col-sm-2 col-form-label">Confirm Password</label>
	    <div class="col-sm-6">
	      <input type="password" class="form-control" id="confirmPass" name="confirmPass" placeholder="">
	    </div>
	  </div>
	  <div class="registerButton">
	  	<button type="submit" class="btn btn-primary">Sign Up</button>
	  </div>
	</form>
	<div class="error">
		${error}
	</div>
</div>

</body>
</html>