<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - User Profile</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/userProfile.css">
	
	<script type="text/javascript" src="/resources/js/userProfile.js"></script>
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
<%--<%= session.getAttribute("customer") %> --%>
<div class="pageTitle"> User Profile</div>
<br/>
<br/>
<div class="userProfileForm">
	<form name="profileForm" action="updateUser" method="post" onsubmit="return validateForm()">
	  <div class="form-group row">
	    <label for="firstname" class="col-sm-2 col-form-label">First Name</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="firstname" name="firstname" value="${customer.getFirst_name()}">
	    </div>
	    <label for="city" class="col-sm-2 col-form-label">City</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="city" name="city" value="${zipcode.getCity()}" readonly>
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="lastname" class="col-sm-2 col-form-label">Last Name</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="lastname" name="lastname" value="${customer.getLast_name()}">
	    </div>
	    <label for="state" class="col-sm-2 col-form-label">State</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="state" name="state" value="${zipcode.getState()}" readonly>
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="address1" class="col-sm-2 col-form-label">Address Line 1</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="address1" name="address1" value="${customer.getAddress_line_1()}">
	    </div>
	    <label for="zipcode" class="col-sm-2 col-form-label">Zipcode</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="zipcode" name="zipcode" value="${customer.getZipcodeText()}">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="address2" class="col-sm-2 col-form-label">Address Line 2</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="address2" name="address2" value="${customer.getAddress_line_2()}">
	    </div>
	    <label for="country" class="col-sm-2 col-form-label">Country</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="country" name="country" value="${zipcode.getCountry()}" readonly>
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="phone" class="col-sm-2 col-form-label">Phone</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="phone" name="phone" value="${customer.getPhone()}">
	    </div>
	    <label for="email" class="col-sm-2 col-form-label">Email</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="email" name="email" value="${customer.getE_mail()}">
	    </div>
	  </div>
	  <div class="updateProfileButton">
	  	<button type="submit" class="btn btn-primary">Update Information</button>
	  </div>
	</form>
	<div class="error">
		${error}
	</div>
</div>
</body>
</html>