<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sizeList==0}">
		<p>L'attore ${actor.firstname} ${actor.lastname} non ha partecipato ad alcun film!</p>
	</c:if>
	<c:if test="${sizeList>0}">
		<p>Di seguito sono elencati tutti i film cui l'attore ${actor.firstname} ${actor.lastname} ha partecipato:</p>
		<table>
			<tr>
				<th>Titolo</th>
			</tr>
			<c:forEach items="${film}" var="riga">
			<tr>
				<td><c:out value="${riga.title}"></c:out></td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
	<p><a href="index.jsp">Torna alla home</a></p>
	
</body>
</html>