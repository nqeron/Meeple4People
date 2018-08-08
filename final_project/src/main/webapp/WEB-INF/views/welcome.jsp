
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Test game recommendation</h2>
${test}
${gameList}
<ol>

<c:forEach var="game" items="${gameList}">
<li>${game}</li>

</c:forEach>
</ol>
</body>
</html>