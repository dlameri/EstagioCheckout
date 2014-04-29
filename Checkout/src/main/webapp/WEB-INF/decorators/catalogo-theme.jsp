<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.jsp.PageContext" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><decorator:title default="Ideais Electronics"/></title>

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.bxslider/jquery.bxslider.js"/>"/>
	<script type="text/javascript" src="<c:url value="/resources/js/javascript-home.js"/>"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="<c:url value="/resources/js/jquery.bxslider/jquery.bxslider.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/simpletabs.css"/>" />
	<script type="text/javascript" src="<c:url value="/resources/js/simpletabs.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.0.js" />"></script>
	
</head>
<body>

	<header id="main-header">
		<div class="container-header">
			<h1 class="logo"><a href="http://ideaiselectronics.com:8081/Catalogo/">Ideais Electronics</a></h1>
			
			<div id="search-area">
				<form method="get" action="${pageContext.request.contextPath}/product/search" name="searchProduct">
					<div class="search-box">
						<input type="text" name="name" id="input-search-text">
						<input type="submit" value="Pesquisar" id="input-search-submit" class="search-icon">
					</div>
				</form>
			</div>
			
			<div class="login-or-register">
			
				<c:if test="${customerName != null}">
					<span>Olá, ${customerName}!</span>
					<a href="http://ideaiselectronics.com:9082/Checkout/customer/customerDetails">Minha conta</a>
					<a href="http://ideaiselectronics.com:9082/Checkout/customer/authenticate/logout">Logout</a>
				</c:if>
				
				<c:if test="${customerName == null}">
					<span>Olá, visitante!</span>
					<a href="http://ideaiselectronics.com:9082/Checkout/customer/new">Cadastre-se</a>
					<a href="http://ideaiselectronics.com:9082/Checkout/customer/authenticate/loginForm">Entre</a>
				</c:if>
					
			</div>

			<div class="cart">
				<a href="http://ideaiselectronics.com:9082/Checkout/shoppingCart/"><span class="shopping-cart">Carrinho</span></a>
				<span class="qtCart">${cartQtdItens}</span>
			</div>

			<nav id="main-nav" class="main-menu">
				<ul class="menu">
					<c:forEach items="${categories}"  var="category">
						<li class="menu-item"><a href="${pageContext.request.contextPath}/category/${category.id}/product">${category.name}</a>
							<ul class="sub-menu">
								<c:forEach items="${category.subcategories}" var="subcategory">
									<li class="submenu-item"><a href="${pageContext.request.contextPath}/category/subcategory/${subcategory.id}/product">${subcategory.name}</a></li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</nav>		
		</div>
	</header>	
	
	<decorator:body />
	
	<c:import url="/WEB-INF/jsp/footer.jsp"/>
	
</body>
</html>