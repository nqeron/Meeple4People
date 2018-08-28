<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - Review</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/reviewGame.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/js/reviewGame.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="pageTitle">Review Your Experience</div>
<br/>
<br/>
<div class="fullBox">
<div class="gameBox">
	<div class = "picture"> <img alt="image will go here" src=""></div>
	<div class = "nameAndRating">
		<div class = "name"> ${game.getName()}</div>
		<div class="rating"> 
			<i id="0" class = "far fa-star fa-3x starRating"></i>
			<i id="1" class = "far fa-star fa-3x starRating"></i>
			<i id="2" class = "far fa-star fa-3x starRating"></i>
			<i id="3" class = "far fa-star fa-3x starRating"></i>
			<i id="4" class = "far fa-star fa-3x starRating"></i>
		</div>
	</div>
</div>
<div class = "commentFormBox">
	<form name="commentForm" action="/addReviewToGame" method="post" onsubmit="return validateForm()">
		<input type="hidden" id="gameId" name="gameId" value="${game.getId()}">
		<input type="hidden" id="ratingVal" name="ratingVal" value="">
		<div class="form-group col-md-6">
			<label for="commentText"> Comments </label>
			<textarea class="form-control" id="commentText" name="commentText" rows="5"></textarea>
		</div>
		<button type="submit" class="reviewBtn">Review</button>
		<button class="skipBtn" onclick="window.location = '/rentals';">Skip</button>
	</form>
</div>
</div>


</body>
</html>