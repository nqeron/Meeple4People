<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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