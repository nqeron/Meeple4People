<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - ${game.getName()}</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/gameDetail.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="pageTitle">${game.getName()}</div>
<br/>
<br/>
<div class="gameBox">
	<div class = "mechanicsBox">
		<div class = "mechanicsTitle"> Mechanics</div>
		<div class = "mechanicsList">
			<ul>
				<c:forEach var="mech" items="${mechanics}">
					<li>
						<a href = "/mechanics/${mech.getId()}" > ${mech.getName()} </a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="leftSide">
		<div class = "top">
			<div class="description">${game.getDescription()}</div>
			<div class="designers">
				Designers:
				<ul>
				<c:forEach var="des" items="${designers}">
					<li>
					 <a href="${des.getWebsite()}"> ${des.getLast_name()}, ${des.getFirst_name()} </a>
					</li>
				</c:forEach>
				</ul>
			</div>
			<div class="publishers">
				Publishers:
				<ul>
				<c:forEach var="pub" items="${publishers}">
					<li>
					 <a href="${pub.getWebsite()}"> ${pub.getName()} </a>
					</li>
				</c:forEach>
				</ul>
			</div>
			<div class="picAndRating">
				<div class="picture"> <image class="gamePic" src="" alt="image will go here" /> </div>
				<div class="averageRating">
					<c:set var="avgRating" value = "${game.getAverage_Rating()}"></c:set>
						<c:choose>
							<c:when test="${avgRating >= 1 }">
								<i class = "fas fa-star fa-2x"></i>
							</c:when>
							<c:when test="${avgRating >= 0.5}">
								<i class = "fas fa-star-half-alt fa-2x"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star fa-2x"></i>		
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${avgRating >= 2 }">
								<i class = "fas fa-star fa-2x"></i>
							</c:when>
							<c:when test="${avgRating >= 1.5}">
								<i class = "fas fa-star-half-alt fa-2x"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star fa-2x"></i>		
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${avgRating >= 3 }">
								<i class = "fas fa-star fa-2x"></i>
							</c:when>
							<c:when test="${avgRating >= 2.5}">
								<i class = "fas fa-star-half-alt fa-2x"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star fa-2x"></i>		
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${avgRating >= 4 }">
								<i class = "fas fa-star fa-2x"></i>
							</c:when>
							<c:when test="${avgRating >= 3.5}">
								<i class = "fas fa-star-half-alt fa-2x"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star fa-2x"></i>		
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${avgRating >= 5 }">
								<i class = "fas fa-star fa-2x"></i>
							</c:when>
							<c:when test="${avgRating >= 4.5}">
								<i class = "fas fa-star-half-alt fa-2x"></i>
							</c:when>
							<c:otherwise>
								<i class = "far fa-star fa-2x"></i>		
							</c:otherwise>
						</c:choose>
				</div>
			</div>
			
			<div class="yearPublished">Published: ${game.getYear_published()}</div>
			
			
		</div>
		<hr>
		<div class = "ratings">
			<c:forEach var="rate" items="${ratings}">
				<div class="ratingStars">
					<c:set var="testRating" value = "${rate.getRating()}"></c:set>
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
				<div class = "ratingText">
					${rate.getComment_text()}
				</div>
			</c:forEach>
		</div>
		<div class = "addToShoppingCart"> 
			<form action="/addGameToShoppingCart" method = "post">
				<button class="addToShoppingCartBtn" type="submit" name="gameId" value="${game.getId()}">
	        			<img src="https://png.icons8.com/ios/100/000000/add-shopping-cart-filled.png">
	        	</button>
			</form>
			<!-- <a href="/addGameToShoppingCart/${game.getId()}"><img src="https://png.icons8.com/ios/100/000000/add-shopping-cart-filled.png"> </a>  -->
		</div>
	</div>
</div>
</body>
</html>