<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Ordem de compra</title>
	</head>
	<body>
	
		<div id="content-container">
		
			<div class="orderContainer">
				<h1 class="title-font">Detalhes do pedido número ${order.id}</h1>
											      
				<c:if test="${errorMessage != null}">
					<div class="errormsgbox">${errorMessage}</div>
				</c:if>
				
				<div  id="customerDetails">
					<div class="containerNewAddress">
						<p class="customer-subtitle">Informações da compra</p>
					
						Número do pedido: <span class="orderInfo">${order.id}</span> <br />
						Data do pedido: <span class="orderInfo">${order.formattedPurchaseDate}</span> <br />
	
						<br />
						<br />
						<br />
						
						<p class="customer-subtitle">Dados de pagamento</p>
						Tipo de pagamento: <span class="orderInfo">${order.payment.typeOfPayment}</span> <br />
						
						<c:if test="${order.payment.typeOfPayment eq 'Cartão de crédito'}">
							Parcelamento: <span class="orderInfo">${order.payment.installments}</span>
						</c:if>

					</div>
					
					<div class="mainAddressCustomer">
						<p class="customer-subtitle">Endereço de entrega</p>
						
						${order.shippingAddress.street}, ${order.shippingAddress.number}, ${order.shippingAddress.complement}<br />
					    ${order.shippingAddress.city}, ${order.shippingAddress.state} - ${order.shippingAddress.zipCode} <br/> <br />  <br /><br />
					    
					    <p class="customer-subtitle">Endereço de cobrança</p>
						
						${order.billingAddress.street}, ${order.billingAddress.number}, ${order.billingAddress.complement}<br />
					    ${order.billingAddress.city}, ${order.billingAddress.state} - ${order.billingAddress.zipCode} <br/>
					    
					    <br />
					    <br />
					    
   						<div class="managedCustomer">
							<a class="voltar" href="http://ideaiselectronics.com:9082/Checkout/customer/customerDetails">Voltar para minha conta</a>
						</div>
					</div>
					

					
					<div class="optionsCustomer">
						<a href="javaScript:void(0);" id="orderItems" class="showHideOrderItems">Mostrar itens do pedido</a>
					</div>
				</div>
	
				<div id="orderItemsDetails">
					<table class="cartItems">
						<thead class="topBox">
							<tr class="cartTableTitles">
								<th class="col01" scope="col" colspan="2">Produto</th>
								<th class="col02" scope="col">Quantidade</th>
								<th class="col03" scope="col">Valor Unitário</th>
								<th class="col03" scope="col">Valor Total</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${order.shoppingCart.shoppingCartLines}" var="line" varStatus="status">
									<tr class="gridProducts">
										<th rowspan="1">
											<a href="#" class="image">
												<img class="photo" src="${line.item.imageUrl}" height="90px" />
											</a>
										</th>
										
										<th class="products">
											<div class="product">
												<span id="productTextName" class="cont">
													<strong class="product-name">
														${line.item.productName} 
													</strong>
												</span>
											</div>
										</th>
										
										<td rowspan="1" class="col02">
											<div class="quantityOrder">${line.quantity}</div>
									   	</td>
						
										<td class="col04">
										   	<div class="valores"> ${line.formattedUnitPrice}
										   	</div>
										</td>
										
										<td class="col05" >
											<div class="valores"> ${line.formattedPrice}</div>
										</td>
			
									</tr>
								
			            	</c:forEach>
								
		
						</tbody>
							
						<tfoot class="cartTotal" class="valores">
								
							<tr class="cartTotal" class="valores">
								<td colspan="3"><span class="ico"></span></td><th class="sp tp1">FRETE:</th>
								<td>
									<span> ${order.shoppingCart.formattedFreight}</span>
								</td>
							</tr>
							
							<tr class="cartTotal" class="valores">
								
								<th scope="row" colspan="4">TOTAL DE PRODUTOS:</th>
								<td>
									<span>${order.shoppingCart.quantityOfItems}</span>
								</td>
							</tr>
		
							<tr class="totalAmount" class="valores">
								
								<th scope="row" colspan="4">TOTAL:</th>
								<td>
									<span> ${order.shoppingCart.formattedTotalAmount}</span>
								</td>
							</tr>
						
						</tfoot>
					</table>
				</div>
			</div>	        
		</div><!-- #content -->
		
	</body>

</html>