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
				
					Nome completo: <span class="orderInfo">${customer.name} ${customer.surname}</span> <br />
					Telefone: <span class="orderInfo">${customer.phoneNumber}</span> <br />
					Email: <span class="orderInfo">${customer.email}</span> <br >
					CPF: <span class="orderInfo">${customer.cpf}</span>
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
						
					<a href="javaScript:void(0);" id="orders" class="showHideOrders">Mostrar compras</a>
				</div>
			</div>
			
			<div id="orderList">
					
					<c:if test="${customer.purchaseOrders != null && !empty customer.purchaseOrders}">
						<p class="customer-subtitle">Compras</p>									
					
						<c:forEach items="${customer.purchaseOrders}" var="order" varStatus="status">
							<div class="orderListUnit">
								Número do pedido: ${order.id} <span class="right-values">Data do pedido: ${order.formattedPurchaseDate}</span></br>  
								Total: ${order.formattedTotalAmount} Frete: ${order.formattedFreight} </br>  
								Frete: ${order.formattedFreight} 
								
								<c:if test="${order.statusOfOrder eq 'Finalizada'}">
									<span class="fineshedOrder"> ${order.statusOfOrder}</span> 
								</c:if>
								
								<c:if test="${order.statusOfOrder eq 'Cancelada'}">
									<span class="closedOrder"> ${order.statusOfOrder}</span> 
								</c:if>
								
								<span class="right-values">Status: </span>
								
								
								<br />
								<br />
								<a href="http://ideaiselectronics.com:9082/Checkout/customer/orderDetails/${order.id}" class="edit">Ver detalhes</a> 
							</div>
						</c:forEach>	
					</c:if>
					
					<c:if test="${customer.purchaseOrders == null || empty customer.purchaseOrders}">
						<p class="customer-subtitle">Você não efetuou nenhuma compra</p>									
					</c:if>
				</div>	
			
		</div>

	</body>
</html>