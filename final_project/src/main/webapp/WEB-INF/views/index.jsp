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
	<jsp:include page="header.jsp"></jsp:include>
		<div id = "Grid">
			<div class ="pageTitle">Recommended Games</div>
			<br/>
			<br/>
			<div class="prevGames">
				<c:if test="${recommended - size > 0}"> 
					<form action="/" method="post">
		        		<button type="submit" name="nextRecommended" value="${recommended - size}">
		        			<i class="fas fa-caret-left fa-5x"></i>
		        		</button>
	    			</form>
    			</c:if>
    		</div>
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
									<image src="${gamePictures.get(game).getUriResource()}" alt="image will go here" height="180" width="220"/>
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
				<c:if test="${!pastNumGames}">
					<form action = "/" method = "post">
		        		<button type="submit" name="nextRecommended" value="${recommended}">
		        			<i class="fas fa-caret-right fa-5x"></i>
		        		</button>
	    			</form>
    			</c:if>
    		</div>
		</div>
	</div>
</body>
</html>
