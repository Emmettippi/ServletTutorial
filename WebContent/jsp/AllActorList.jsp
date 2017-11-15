<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actor List</title>
</head>
<body>
	<p>Here's the actor list</p>
	<table>
		<tr>
			<th>Firstname</th>
			<th>Lastname</th>
		</tr>
		<c:forEach items="${actors}" var="riga">
		<tr>
			<td><c:out value="${riga.firstname}"></c:out></td>
			<td><c:out value="${riga.lastname}"></c:out></td>
			<td><a href="http://localhost:8080/Esercizio_1_BD_SQL/set_actors?id=${riga.id}">Modifica</a></td>
		</tr>
		</c:forEach>
	</table>
	<form action="set_actors">
		<input type="submit" value="Click here"> to add a new actor.
		<input type="hidden" name="id" value="0">
	</form>
	<p><a href="index.jsp">Torna alla home</a></p>
</body>
</html>