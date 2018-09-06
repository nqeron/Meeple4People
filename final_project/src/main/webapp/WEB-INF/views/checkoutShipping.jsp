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
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/js/changeZip.js"></script>
	<script type="text/javascript" src="/resources/js/checkoutShipping.js"></script>
	<script type="text/javascript">
		function useProfile(){
			var useProfile = document.getElementById("useProfileCheck").checked;
			
			var firstname = document.getElementById("firstname");
			var lastname = document.getElementById("lastname");
			var city = document.getElementById("city");
			var state = document.getElementById("state");
			var address1 = document.getElementById("address1");
			var address2 = document.getElementById("address2");
			var zipcode = document.getElementById("zipcode");
			var country = document.getElementById("country");
			var phone = document.getElementById("phone");
			
			firstname.value = useProfile ? "${customer.getFirst_name()}" : "";
			lastname.value = useProfile ? "${customer.getLast_name()}" : "";
			city.value = useProfile ? "${zipcode.getCity()}" : "";
			state.value = useProfile ? "${zipcode.getState()}" : "";
			address1.value = useProfile ? "${customer.getAddress_line_1()}" : "";
			address2.value = useProfile ? "${customer.getAddress_line_2()}" : "";
			zipcode.value = useProfile ? "${customer.getZipcode()}" : "${allZips.get(0).getZipcode()}";
			country.value = useProfile ? "${zipcode.getCountry()}" : "";
			phone.value = useProfile ? "${customer.getPhone()}" : "";
		}
		
	</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<%--<%= session.getAttribute("customer") %> --%>
<div class="pageTitle"> Checkout -- Shipping Info</div>
<br/>
<br/>
<div class="userProfileForm">
	<form name="shippingForm" action="/checkout/billing" method="post" onsubmit="return validateForm()">
		<div class = "useProfile">
			<label for="useProfileCheck">Use data from user profile</label>
			<input type="checkbox"  id="useProfileCheck" onclick="useProfile()">
		</div>	
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
	    	<select class="custom-select" id="zipcode" name="zipcode" onChange="changeZip()">
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
	      <input type="tel" class="form-control" id="phone" name="phone" value="${customer.getPhone()}">
	    </div>
	  </div>
	  <div class = "goToShoppingCart">
	  	<a href="/shoppingCart">Go Back</a><!-- <button></button>  -->
	  </div>
	  <div class="goToBilling">
	  	<button type="submit" class="btn btn-primary">Proceed</button>
	  </div>
	</form>
	<div class="error">
		${error}
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>