
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function doThis(){
		var erMesg = document.getElementById("errorMessage");
		erMesg.value = "${error}";
	}
</script>
</head>
<body>
<h2>Test java script</h2>
${error}
<form>
	<input id="errorMessage" type="text" name="errorMessage">
	<input type="button" onclick="doThis()"> 
</form>
</body>
</html>