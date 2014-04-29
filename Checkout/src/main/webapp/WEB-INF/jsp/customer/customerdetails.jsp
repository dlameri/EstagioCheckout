<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Minha conta</title>
		<meta charset="utf-8">
	</head>
	<body>
	    <div id="content-container">
			<div  id="customerDetails">
				${customer.name} ${customer.surname} <br />
				Endereço principal <br />
				${customer.mainAddress.street}, ${customer.mainAddress.number}, ${customer.mainAddress.city}, ${customer.mainAddress.state} - ${customer.mainAddress.zipCode} <br/>
				<br />
				editar informações <br />
				
				vizualizar meus pedidos
			
			</div>
		</div>

	</body>
</html>