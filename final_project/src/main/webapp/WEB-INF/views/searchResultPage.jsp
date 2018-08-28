<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - Search Results</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/searchResultPage.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<div class="pageTitle"> Search Results</div>
<div class="searchTerms">Search Query: ${searchString}</div>
<br/>
<br/>
<c:choose>
	<c:when test="${games.isEmpty()}"> <h3>No Games Found, please try a different query</h3></c:when>
	<c:otherwise>
		<div class="resultsContainer">
		<c:forEach var="game" items="${games}">
			<div class = "gameItem">
				<div class = "picture">
					<img src="${gamePictures.get(game).getUriResource()}" alt="image will go here" width="110" height="90">
				</div>
				<div class = "nameRating">
					<div class = "name"> ${game.getName()}</div>
					<div class = "rating"> 
						<c:set var="avgRating" value = "${game.getAverage_Rating()}"></c:set>
						<c:choose>
							<c:when test="${avgRating >= 1 }">
								<i class = "fas fa-star"></i>
							</c:when>
							<c:when test="${avgRating >= 0.5}">
								<i class = "fas fa-star-half-alt"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star"></i>		
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${avgRating >= 2 }">
								<i class = "fas fa-star"></i>
							</c:when>
							<c:when test="${avgRating >= 1.5}">
								<i class = "fas fa-star-half-alt"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star"></i>		
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${avgRating >= 3 }">
								<i class = "fas fa-star"></i>
							</c:when>
							<c:when test="${avgRating >= 2.5}">
								<i class = "fas fa-star-half-alt"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star"></i>		
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${avgRating >= 4 }">
								<i class = "fas fa-star"></i>
							</c:when>
							<c:when test="${avgRating >= 3.5}">
								<i class = "fas fa-star-half-alt"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star"></i>		
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${avgRating >= 5 }">
								<i class = "fas fa-star"></i>
							</c:when>
							<c:when test="${avgRating >= 4.5}">
								<i class = "fas fa-star-half-alt"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star"></i>		
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class = "description"> ${game.getDescription()}</div>
				<div class = "yearPublished"> ${game.getYear_published()}</div>
				<div class = "designers">
					Designers:
					<ul>
						<c:forEach var="des" items="${designers.get(game)}">
							<li>
								<a href="${des.getWebsite()}"> ${des.getLast_name()}, ${des.getFirst_name()} </a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="publishers">
					Publishers:
					<ul>
						<c:forEach var="pub" items="${publishers.get(game)}">
							<li>
								<a href="${pub.getWebsite()}"> ${pub.getName()} </a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="mechanics">
					Mechanics:
					<ul>
						<c:forEach var="mech" items="${mechanics.get(game)}">
							<li>
								<a href="/mechanics/${mech.getId()}"> ${mech.getName()} </a>
							</li>
						</c:forEach>
					</ul> 
				</div>
				<div class="addToCart">
					<form action="/addGameToShoppingCart" method = "post">
						<button class="addToShoppingCartBtn" type="submit" name="gameId" value="${game.getId()}">
	        				<img src="https://png.icons8.com/ios/64/000000/add-shopping-cart-filled.png">
	        			</button>
					</form>
					<div class="error">${error}</div>
				</div>
			</div>
			<hr>
		</c:forEach>
		</div>
	</c:otherwise>
</c:choose>

<!-- <div> page: ${page}</div>  -->

</body>
</html>