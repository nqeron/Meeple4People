<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meeple4People - Order Details</title>
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="/resources/css/base.css">
	<link rel="stylesheet" href="/resources/css/orderDetail.css">
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="pageTitle">Order Details: ${orderId}</div>
<br/>
<br/>
<div class = "GameList">
	<c:forEach var = "game" items="${games}">
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


</body>
</html>