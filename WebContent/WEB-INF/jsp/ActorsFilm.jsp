<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>There you go</title>
</head>
<body>
	<p>Here's the list of all actors who made the movie: ${ritorno.film}</p>
	<table>
		<tr>
			<th>Firstname</th>
			<th>Lastname</th>
		</tr>
		<c:forEach items="${ritorno.actors}" var="riga">
		<tr>
			<td><c:out value="${riga.firstname}"></c:out></td>
			<td><c:out value="${riga.lastname}"></c:out></td>
		</tr>
		</c:forEach>
	</table>
	<p><a href="http://localhost:8080/FilmActor/">Torna alla home</a></p>

</body>
</html>