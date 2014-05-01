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
	    	<h1 class="title-font">Minha conta</h1>
			<div  id="customerDetails">
				<div class="containerNewAddress">
					<p class="customer-subtitle">Informações pessoais</p>
				
					${customer.name} ${customer.surname} <br />
					${customer.phoneNumber} <br />
					${customer.email}
					<br />
				</div>
				
				<div class="mainAddressCustomer">
					<p class="customer-subtitle">Endereço principal</p>
					
					${customer.mainAddress.street}, ${customer.mainAddress.number}, ${customer.mainAddress.complement}<br />
				    ${customer.mainAddress.city}, ${customer.mainAddress.state} - ${customer.mainAddress.zipCode} <br/>
				</div>
				
				<div class="optionsCustomer">
					<div class="managedCustomer">
						<a href="edit" class="edit">editar informações</a>
					</div> 
					
					<br />	
						
					<a href="#" id="orders" class="showHideOrders">Mostrar compras</a>
				</div>
			</div>
			
			<div id="orderList">
					<p class="customer-subtitle">Compras</p>									
				
					<c:forEach items="${customer.deliveryAddresses}" var="shippingAddress" varStatus="status">
						<div class="orderListUnit">
							Destinatário: ${order.addressee} <br />
							${shippingAddress.street}, ${shippingAddress.number} <br />
							${shippingAddress.neighborhood} ${shippingAddress.city}, ${shippingAddress.state} - ${shippingAddress.zipCode} <br />
						</div>
					</c:forEach>	
				</div>	
			
		</div>

	</body>
</html>