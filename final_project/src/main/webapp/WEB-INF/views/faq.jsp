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
<jsp:include page="header.jsp"></jsp:include>

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
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>