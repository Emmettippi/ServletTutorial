<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="modification_complete">
		Insert First Name: <input type="text" name="firstname" value="${actor.firstname}" size="20px"> <br>
		Insert Last Name: <input type="text" name="lastname" value="${actor.lastname}" size="20px"> <br><br>
		<input type="submit" value="Click here"> to confirm.
		<input type="hidden" name="id" value="${actor.id}">
	</form>
</body>
</html>