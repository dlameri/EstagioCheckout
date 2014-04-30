<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<<<<<<< HEAD:Checkout/src/main/webapp/WEB-INF/jsp/customer/login.jsp
	<form:form class="LoginForm" action="login" autocomplete="off" commandName="" method="POST">
			<form:input type="text" path="username"/>
			<form:input type="password" path="password"/>
			<form:button class="button" type="submit">Cadastrar</form:button>
	</form:form>
=======
<h1><%= request.getAttribute("message") %></h1>
>>>>>>> 5c139a612973a0d107791798564899a4d228ab6e:Checkout/src/main/webapp/WEB-INF/jsp/auth/success.jsp
</body>
</html>