<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<link href='http://fonts.googleapis.com/css?family=Dosis' rel='stylesheet' type='text/css'>
		<link rel="stylesheet" href="<c:url value="/resources/css/error.css"/>">
		
		<title>Página não encontrada</title>
	</head>

	<body class="badRequest">
		<div>
			<strong class="ops">Ops!</strong>
			<span class="erro-http"> 400 </span>
			<strong class="nao-encontrada"> "Bad Request?" </strong>
			
			
			<a href="javascript:window.history.go(-1)" class="walk-on-home-boy">
				<div class="seta-esquerda"></div>
				VOLTAR 
			</a>
			
		</div>	
		
	</body>
</html>