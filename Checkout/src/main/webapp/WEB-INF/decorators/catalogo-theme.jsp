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
	<script type="text/javascript" src="<c:url value="/resources/js/jquery.bxslider/jquery.bxslider.js"/>"/>
	<script type="text/javascript" src="<c:url value="/resources/js/javascript-home.js"/>"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" href="<c:url value="/resources/js/jquery.bxslider/jquery.bxslider.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/simpletabs.css"/>" />
	<script type="text/javascript" src="<c:url value="/resources/js/simpletabs.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.0.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.validationEngine-pt_BR.js"/>" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value="/resources/js/jquery.validationEngine.js" />" type="text/javascript" charset="utf-8"></script>
	<script src="<c:url value="/resources/js/validateForm.js" />"type="text/javascript"></script>
	<script src="<c:url value="/resources/js/jquery.mask.min.js" />"type="text/javascript"></script>
	<link rel="stylesheet" href="<c:url value="/resources/css/validationEngine.jquery.css"/>"/>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	
	<link rel="stylesheet" href="<c:url value="/resources/css/lightbox.css"/>"/>
	<script type="text/javascript" src="<c:url value="/resources/js/lightbox.js"/>"></script>
	
	<link rel="stylesheet" href="<c:url value="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/base/jquery-ui.css"/>"/>
<script type="text/javascript">
$(function() {
	$('#expiryDate').datepicker( {
		monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		closeText: 'confirmar',
		currentText: 'hoje',
		changeMonth: true,
		changeYear: true,
		showButtonPanel: true,
		dateFormat: 'mm/yy',
		minDate:'m', // restrict to show month greater than current month
		onClose: function(dateText, inst) {
		// set the date accordingly
		var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
		var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
		$(this).datepicker('setDate', new Date(year, month, 1));
		},
		beforeShow : function(input, inst) {
			if ((datestr = $(this).val()).length > 0) {
				year = datestr.substring(datestr.length-4, datestr.length);
				month = jQuery.inArray(datestr.substring(0, datestr.length-5), $(this).datepicker('option', 'monthNames'));
				$(this).datepicker('option', 'defaultDate', new Date(year, month, 1));
				$(this).datepicker('setDate', new Date(year, month, 1));
			}
		}
	});
});
</script>

  
<style type="text/css">.ui-datepicker-calendar { display: none; } #ui-datepicker-div { display:none }
</style>

	
</head>
<body>

	<header id="main-header">
		<div class="container-header">
			
			<h1 class="logo">
			<span class="logotipo"></span>
			<a href="http://ideaiselectronics.com:8081/catalogo/">Ideais Electronics</a></h1>
			
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
						<li class="menu-item"><a href="http://ideaiselectronics.com:8081/catalogo/category/${category.id}/product">${category.name}</a>
							<ul class="sub-menu">
								<c:forEach items="${category.subcategories}" var="subcategory">
									<li class="submenu-item"><a href="http://ideaiselectronics.com:8081/catalogo/subcategory/${subcategory.id}/product">${subcategory.name}</a></li>
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