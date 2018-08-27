<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - Rentals</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/rentals.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class = "pageTitle">Rentals</div>
<br/>
<br/>
<div class = "Overview">
	<div class = "GameList">
		<c:forEach var = "game" items="${gamesRented.keySet()}">
			<c:set var="rental" value="${gamesRented.get(game)}" />
			<div class="GameItem">
				<div class = "picture">
					<img src="" alt="image will go here">
				</div>
				<div class="name">${game.getName()}</div>
				<div class="rentalDate">Date rented: ${rental.getDate_rented()}</div>
				<div class="dueDate" <c:if test="${rental.isOverdue()}">style="color:red"</c:if> >Due date: ${rental.getDue_date()}</div>
				<div class="return"> 
					<form action="/returnGame" method="post"> 
						<button class="returnBtn" type="submit" name="rentalId" value="${rental.getId()}">
							<img src="https://png.icons8.com/metro/52/000000/return-purchase.png">
						</button>
					</form>
				</div>
			</div>
			<hr>
		</c:forEach>
	</div>
	<div class="balance">Balance: ${customer.getBalanceText()}</div>
</div>
</body>
</html>