<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - Confirmation</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/checkoutReview.css">
	<style>
		.OrderConf{
			text-align: center;
		}
	</style>
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
<div class = "pageTitle">Confirmation</div>
<br/>
<br/>
<div class = "Review">
	<div class = "OrderConf"><p>Thank You for your order</p> <p> Your order number is <a href="/orders/${orderId}">${orderId} </a> </p></div>
	<div class = "GameList">
		<c:forEach var = "mapItem" items="${itemGames.keySet()}">
			<c:set var="game" value="${itemGames.get(mapItem)}" />
			<div class="GameItem">
				<div class = "picture">
					<img src="" alt="image will go here">
				</div>
				<div class="name">${game.getName()}</div>
				<div class="description">${game.getDescription()}</div>
			</div>
			<hr>
		</c:forEach>
	</div>
	<div class ="checkoutDetails">
		<div class="shippingReview">
			<span class="reviewLabel">Shipping: </span>
			<pre>
${shipCust.getFirst_name()} ${shipCust.getLast_name()}
${shipCust.getAddress_line_1()}
${shipCust.getAddress_line_2()}
${shipCustZip.getCity()}, ${shipCustZip.getState()} ${shipCustZip.getZipcodeText()}
			</pre>
		</div>
		<div class="BillingReview">
			<span class="reviewLabel">Billing: </span>
			<pre>
${billCust.getFirst_name()} ${shipCust.getLast_name()}
${billCust.getAddress_line_1()}
${billCust.getAddress_line_2()}
${billCustZip.getCity()}, ${billCustZip.getState()} ${billCustZip.getZipcodeText()}
				
Credit Card #: ${creditCard}
			</pre>
		</div>
	</div>
</div>
<div class = "bottom">
	<hr>
	<div class="rentalSum">Price: ${rentalSum}</div>
	<hr>
	<div class="shippingCost">Shipping: ${shippingCost}</div>
	<hr>
	<div class="backToStore">
		<a href="/">Go back to Store</a>
	</div>
	<div class="sum"> Total: ${totalSum}</div>
</div>
</body>
</html>