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
	<c:if test="${error==0}">
		<p>Here's the list of all actors who made the movie: ${film}</p>
		<table>
			<tr>
				<th>Firstname</th>
				<th>Lastname</th>
			</tr>
			<c:forEach var="k" begin="1" end="${i}">
			<tr>
				<td><c:out value="${actor[k].firstname}"></c:out></td>
				<td><c:out value="${actor[k].lasstname}"></c:out></td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${error==1}">
		<p>Il film inserito non è presente nel DB. Siamo spiacenti.</p>
	</c:if>
	<c:if test="${error==2}">
		<p>Nessun attore ha partecipato a ${film}.</p>
	</c:if>
	<p><a href="EsPrepareStatement.jsp">Torna alla home</a></p>

</body>
</html>