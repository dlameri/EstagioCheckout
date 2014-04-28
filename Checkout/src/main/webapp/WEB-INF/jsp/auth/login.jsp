<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LOGIN</h1>
	<form:form action="login" commandName="loginForm">

		<form:input type="text" path="userName" />
		<form:password path="password" />
		<input type="submit" value="LOGIN"/>
	</form:form>
</body>
</html>