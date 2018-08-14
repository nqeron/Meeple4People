<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - FAQ</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/faq.css">
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
          		<input type="text" class="form-control" placeholder="Search">
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

<div class="pageTitle">FAQ</div>
<br/>
<br/>

<div class = "faqText">
	<ul>
		<li><p class = "question">What does it cost to rent a game?</p> <p class="answer">It costs $5.00 per game</p></li>
		<li><p class = "question">What happens if I fail to return a game?</p> <p class="answer">You will be suspended from borrowing more games and have a balance added to your account equal to the cost of the game</p></li>
		<li><p class = "question">What if a game comes missing pieces?</p> <p class="answer">Let us know, return the game, and we will refund you your purchase after verifying the claim.</p></li>
		<li><p class = "question">How can I add a review to a game?</p> <p class="answer">You may only review games after renting and returning them.</p></li>
		<li><p class = "question">How can I add a game to my shopping cart?</p> <p class="answer">You can add a game to your shopping cart from the game's detail page or from a search page by clicking the corresponding shopping cart with a plus mark icon.</p></li>
	</ul>
	<p> If none of these answer your question, you can e-mail us at <a href="mailto:support@meeple4people.com"> support@meeple4people.com</a>. 
	</p>
</div>
</body>
</html>