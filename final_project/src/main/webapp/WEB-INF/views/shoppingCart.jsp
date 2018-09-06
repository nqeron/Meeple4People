<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - Shopping Cart</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/shoppingCart.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class = "pageTitle">Shopping Cart</div>
<br/>
<br/>
<div class = "GameList">
	<c:forEach var = "mapItem" items="${itemGames.keySet()}">
		<c:set var="game" value="${itemGames.get(mapItem)}" />
		<div class="GameItem">
			<div class="removeItem">
				<form action="/removeItemFromShoppingCart" method="post">
					<button class="removeBtn" type="submit" name="itemId" value="${mapItem.getItem_id()}">
						<img alt="minus" src="https://png.icons8.com/material-outlined/48/000000/minus.png">
					</button>
				</form>
			</div>
			<div class = "picture">
				<img src="${gamePictures.get(game).getUriResource()}" alt="image will go here" width="110" height="90">
			</div>
			<div class="name">${game.getName()}</div>
			<div class="description">${game.getDescription()}</div>
		</div>
		<hr>
	</c:forEach>
</div>
<hr>
<div class = "bottom">
	<div class="backToStore">
		<a href="/">Go back to Store</a>
	</div>
	<div class="sum"> ${rentalSum}</div>
	<div class="checkout">
		<a href="/checkout/shipping">Checkout</a>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>