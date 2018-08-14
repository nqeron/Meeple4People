<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
	<title>Meeple4People</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/index.css">
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
		<div id = "Grid">
			<div class ="pageTitle">Recommended Games</div>
			<br/>
			<br/>
			<%int i = 0;%>
			<div> 
				<table class = "gameGrid">
				<c:forEach var = "game" items="${gameList}">
					<%if ((i == 0) || (i == 3)) {%> 
						<tr>
					<%} %>
					<td>
						<div id = "game_<%=i %>" class = "game_box">
							<a class="gameLink" href="games/${game.getId()}">
								<div class = "game_image">
									<image src="" alt="image will go here"/>
								</div>
							</a>
							<div class = "game_name">
								${game.getName()}			
							</div>
							<div class = "game_rating">
								<c:set var="testRating" value = "${game.getAverage_Rating()}"></c:set>
								<c:choose>
									<c:when test="${testRating >= 1 }">
										<i class = "fas fa-star"></i>
									</c:when>
									<c:when test="${testRating >= 0.5}">
										<i class = "fas fa-star-half-alt"></i>
									</c:when>
									<c:otherwise>
										<i class = "far fa-star"></i>		
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${testRating >= 2 }">
										<i class = "fas fa-star"></i>
									</c:when>
									<c:when test="${testRating >= 1.5}">
										<i class = "fas fa-star-half-alt"></i>
									</c:when>
									<c:otherwise>
										<i class = "far fa-star"></i>		
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${testRating >= 3 }">
										<i class = "fas fa-star"></i>
									</c:when>
									<c:when test="${testRating >= 2.5}">
										<i class = "fas fa-star-half-alt"></i>
									</c:when>
									<c:otherwise>
										<i class = "far fa-star"></i>		
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${testRating >= 4 }">
										<i class = "fas fa-star"></i>
									</c:when>
									<c:when test="${testRating >= 3.5}">
										<i class = "fas fa-star-half-alt"></i>
									</c:when>
									<c:otherwise>
										<i class = "far fa-star"></i>		
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${testRating >= 5 }">
										<i class = "fas fa-star"></i>
									</c:when>
									<c:when test="${testRating >= 4.5}">
										<i class = "fas fa-star-half-alt"></i>
									</c:when>
									<c:otherwise>
										<i class = "far fa-star"></i>		
									</c:otherwise>
								</c:choose>
						</div>
					</div>
					</td>
					
					<%if ((i == 2) || (i == 5)){  %>
						</tr>
					<% } %>
					<%i = i + 1;%>
				</c:forEach>
			</table>
			</div>
			<div class = "nextGames">
				<form action = "" method = "post">
	        		<button type="submit" name="nextRecommended" value="${recommended}">
	        			<i class="fas fa-caret-right fa-5x"></i>
	        		</button>
    			</form>
    		</div>
		</div>
	</div>
</body>
</html>
