<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - Checkout: Billing Info</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/userProfile.css">
	<style type="text/css">
		.expire{
			display: flex;
		}
	</style>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/js/changeZip.js" ></script>
	<script type="text/javascript" src="/resources/js/checkoutBilling.js" ></script>
	<script type="text/javascript">
		function useProfile(){
			var useProfile = document.getElementById("useShippingAddrCheck").checked;
			
			var fullname = document.getElementById("fullname");
			var city = document.getElementById("city");
			var state = document.getElementById("state");
			var address1 = document.getElementById("address1");
			var address2 = document.getElementById("address2");
			var zipcode = document.getElementById("zipcode");
			var country = document.getElementById("country");
			
			fullname.value = useProfile ? "${shipCust.getFirst_name()} ${shipCust.getLast_name()}" : "";
			city.value = useProfile ? "${zipcode.getCity()}" : "";
			state.value = useProfile ? "${zipcode.getState()}" : "";
			address1.value = useProfile ? "${shipCust.getAddress_line_1()}" : "";
			address2.value = useProfile ? "${shipCust.getAddress_line_2()}" : "";
			zipcode.value = useProfile ? "${shipCust.getZipcode()}" : "${allZips.get(0).getZipcode()}";
			country.value = useProfile ? "${zipcode.getCountry()}" : "";
		}
		
	</script>
</head>
<body>
<div class = "header">
  <div class="main-dropdown">
    <button class="dropbtn"> <img style="height:45px" src="https://png.icons8.com/color/180/drag-list-down.png">
      <!--<i class="fas fa-bars fa-5x"></i>-->
    </button>
      <div class="dropdown-content">
        <a href="/">Home</a>
        <a href="/shoppingCart">Shopping Cart</a>
     	<a href="/about">About</a>
     	<a href="/faq">FAQ</a>
     	<a href="/browseGames">Browse Games</a>
     	<a href="/rentals">Rentals</a>
      </div>
    </div> 
  <div class = "searchBar"><div class="form-group">
          		<form action="/search" method="get">
          			<input type="text" class="form-control" name="searchString" placeholder="Search" />
          			<input type="hidden" name="page" value="1" /> 
          			<input type="submit" style="position: absolute; left: -9999px; width: 1px; height: 1px;" tabindex="-1" />
          		</form>
  </div></div>
  <div class = "userProfile-dropdown">
    
    <button class = "dropbtn"><!--<i class="fas fa-user-circle fa-5x"></i>--> <img style="height:45px" src = "https://png.icons8.com/cotton/40/000000/gender-neutral-user.png" /></button>
    <div class="dropdown-content">
    <c:choose>
    	<c:when test="${customer != null}">
    		<a href="/userProfile"> User Profile</a>
    		<a href="/logout"> Logout</a>
    	</c:when>
    	<c:otherwise>
    		<a href="/login"> Login </a>
      		<a href="/register"> Sign Up </a>
    	</c:otherwise>
    </c:choose>
    </div>
   </div>
  <div class = "shoppingCart"><!--<i class="fas fa-shopping-cart fa-5x"></i>--> <a href="/shoppingCart"> <img style="height:45px" src="https://png.icons8.com/ios/40/000000/shopping-cart-filled.png" /> </a> </div>
</div>
<%--<%= session.getAttribute("customer") %> --%>
<div class="pageTitle"> Checkout -- Billing Info</div>
<br/>
<br/>
<div class="userProfileForm">
	<form name="billingForm" action="/checkout/confirmation" method="post" onsubmit="return validateForm()">
		<div class = "useShipping">
			<label for="useShippingAddrCheck">Same as shipping address</label>
			<input type="checkbox"  id="useShippingAddrCheck" onclick="useProfile()">
		</div>	
	  <div class="form-group row">
	    <label for="fullname" class="col-sm-2 col-form-label">Full Name</label>
	    <div class="col-sm-3 col">
	      <input type="text" class="form-control" id="fullname" name="fullname" value="">
	    </div>
	    <label for="creditCardnum" class="col-sm-2">Credit Card #</label>
	    <div class="col-sm-3">
	    	<input type="text" class="form-control" id="creditCardNum" name="creditCardNum">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="address1" class="col-sm-2 col-form-label">Address Line 1</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="address1" name="address1" value="">
	    </div>
	    <label for="cvv" class="col-sm-2"> CVV </label>
	    <div class="col-sm-3">
	    	<input type="text" class="form-control" id="cvv" name="cvv">
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="address2" class="col-sm-2 col-form-label">Address Line 2</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="address2" name="address2" value="">
	    </div>
	    <label for="expireMM" class="col-sm-2">Expiration date</label>
	    <div class="col-sm-3 expire">
	    	<select class="custom-select" id="expireMM" name="expireMM">
	    		<option value=''>Month</option>
		    	<option value='01'>January</option>
			    <option value='02'>February</option>
			    <option value='03'>March</option>
			    <option value='04'>April</option>
			    <option value='05'>May</option>
			    <option value='06'>June</option>
			    <option value='07'>July</option>
			    <option value='08'>August</option>
			    <option value='09'>September</option>
			    <option value='10'>October</option>
			    <option value='11'>November</option>
			    <option value='12'>December</option>
	    	</select>
	    	<select class="custom-select" id="expireYY" name="expireYY">
	    		<option value=''>Year</option>
	    		<option value='18'>2018</option>
	    		<option value='19'>2019</option>
	    		<option value='20'>2020</option>
	    		<option value='21'>2021</option>
	    		<option value='22'>2022</option>
	    		<option value='23'>2023</option>
	    		<option value='24'>2024</option>
	    		<option value='25'>2025</option>
	    		<option value='26'>2026</option>
	    	</select>
	    </div>
	  </div>
	  <div class="form-group row">
	  	<label for="city" class="col-sm-2 col-form-label">City</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="city" name="city" value="" readonly>
	    </div>
	  </div>
	  <div class="form-group row">
	  	<label for="state" class="col-sm-2 col-form-label">State</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="state" name="state" value="" readonly>
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="country" class="col-sm-2 col-form-label">Country</label>
	    <div class="col-sm-3">
	      <input type="text" class="form-control" id="country" name="country" value="" readonly>
	    </div>
	  </div>
	  <div class="form-group row">
	    <label for="zipcode" class="col-sm-2 col-form-label">Zipcode</label>
	    <div class="col-sm-3">
	    	<select class="custom-select" id="zipcode" name="zipcode" onChange="changeZip()">
	    		<option value="">Zipcode</option>
	    		<c:forEach var = "zip" items="${allZips}">
	    			<c:choose>
	    				<c:when test="${zip.getZipcode() == customer.getZipcode()}">
	    					<option value="${zip.getZipcode()}" selected> ${zip.getZipcode()}</option>	
	    				</c:when>
	    				<c:otherwise>
	    					<option value="${zip.getZipcode()}"> ${zip.getZipcode()}</option>	
	    				</c:otherwise>
	    			</c:choose>
	    			
	    		</c:forEach>
	    	</select>
	      <!-- <input type="text" class="form-control" id="zipcode" name="zipcode" value="${customer.getZipcodeText()}"> -->
	    </div>
	  </div>
	  <div class = "goToShoppingCart">
	  	<a href="/shoppingCart">Go Back</a><!-- <button></button>  -->
	  </div>
	  <div class="goToReview">
	  	<button type="submit" class="btn btn-primary">Proceed</button>
	  </div>
	</form>
	<div class="error">
		${error}
	</div>
</div>
</body>
</html>