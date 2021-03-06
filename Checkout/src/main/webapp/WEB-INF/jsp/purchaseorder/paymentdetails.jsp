<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Loja Virtual - Dados de Pagamento</title>
  		
    </head>
    <body>
	    <div id="content-container">
			<h1 class="payment-font">Pagamento</h1>
				
			<c:if test="${errorMessage != null}">
				<div class="errormsgbox">${errorMessage}</div>
			</c:if>
				
				<div class="box1" class="payment-font">
					
					<div class="sub-box1">
						<p class="payment-subtitle">endereço de entrega</p>
						<div class="shipping-address">
							<c:if test="${errorMessage == null}">
								Destinatário: ${order.addressee} <br />
								${order.shippingAddress.street}, ${order.shippingAddress.number}, ${order.shippingAddress.city}, ${order.shippingAddress.state} - ${order.shippingAddress.zipCode} <br />
								<a class="editShipAddress" href="../customer/address/newAddress"><span class="editAddressShipping">Alterar o endereço de entrega</span></a>
								<a href="../customer/address/editAddressForm/${order.shippingAddress.id}"><span class="editAddressShipping" class="right-values">Editar o endereço atual</span></a>
							</c:if>
						</div>
					</div>
					
					<div class="sub-box2">
						<p class="payment-subtitle">dados de compra</p>
						<div class="payment-data">
							Total em produtos (${order.shoppingCart.formattedQuantityOfItems}) <span class="right-values">${order.shoppingCart.formattedSubTotalAmount}</span><br />
							Frete para ${order.shippingAddress.city}<span class="right-values">${order.formattedFreight}</span> <br />
							<span class="total-order-price">Total: <span class="right-values">${order.shoppingCart.formattedTotalAmount}</span></span>
						</div>
					</div>
				</div>
				
				<div class="space">
				</div>
				
				<p class="payment-subtitle">formas de pagamento</p>
				<div class="payment-options" class="payment-font">
				
					<div class="simpleTabs">
					    <ul class="simpleTabsNavigation">
							
							<div class="tab">
						     	<li>
						     		<div class="payment-type">
 										<img class="photo" src="<c:url value="/resources/images/card_image.jpg" />" />
									</div>
									<a id="creditSelect" href="javaScript:void(0);" class="selectPayment">Selecionar</a>			      			
								</li>
			      			</div>
					      
							<div class="tab">
						     	<li>
						     		<div class="payment-type">
						      			<img class="photo" src="<c:url value="/resources/images/boleto_image.jpg" />" />
									</div>
									<a id="billetSelect" href="javaScript:void(0);" class="selectPayment">Selecionar</a>
				      			</li>
			      			</div>
  
					    </ul>
					    
					    <div id="formCard" class="simpleTabsContent">
					    	<table class="formCreditCard">
					    		<tr>
					    			<td class="label-input">Nome do titular:</td>
					    			<td><input id="holderName" type="text" name="holderName" autocomplete="off"  maxlength="100" class="validate[required]" /></td>
					    		</tr>
					    		<tr>
					    			<td class="label-input">Número do cartão:</td>
					    			<td><input id="cardNumber" type="text" name="cardNumber" autocomplete="off" maxlength="19" class="validate[required] creditcard" /></td>
					    		</tr>
					    		<tr>
					    			<td class="label-input" for="expiryDate">Validade:</td>
					    			<td><input name="cc_expiryDate" id="expiryDate" class="date-picker" />
					    		</tr>
					    		<tr>
					    			<td>Código de segurança do Cartão:</td>
					    			<td><input id="securityCode" type="text" name="securityCode" autocomplete="off" maxlength="4" class="validate[required]"/></td>
					    		</tr>
					    		
					    		<form method="post" action="http://ideaiselectronics.com:9082/Checkout/purchaseOrder/processOrder" name="editQuantity">
					    			<tr>
					    		
						    			<td>Parcelar em:</td>
						    			<td>
							    			<select name="installment">
							    				<c:forEach var="installment" items="${order.installments}">
												    <option value="<c:out value="${installment.formattedValue}" />">
												    	${installment.formattedValue}
												    </option>
												 </c:forEach>
											</select></td>
						    		</tr>
						    		<tr>	
						    			<td>
						    				<input class="credictPayment" type="text" name="paymentType" value="CREDIT_CARD" />
						    			<td>
						    		</tr>
						    		<tr>
						    			<td>
							    			<c:if test="${order.shoppingCart.quantityOfItems > 0}">
				   				   				<div class="finishOrder">
								    				<input type="submit" class="proccessCartBuy" value="Finalizar compra">
							      	 			</div>
							      	 		</c:if>
						      	 		</td>	
					      	 		</tr>
			      	 			
	 							</form>
				      	 	 		
					    	</table>
					   
					    </div>
					    <div id="formBillet" class="simpleTabsContent">
							<img class="billetImage" height="100px" alt="ícone de boleto" src="<c:url value="/resources/images/boleto.png" />">
							<span class="billetMessage">
								Os dados de pagamento da sua compra serão enviados para o seu email.
							</span>
							
							<form method="post" action="http://ideaiselectronics.com:9082/Checkout/purchaseOrder/processOrder" name="editQuantity">
							
								<input class="credictPayment" type="text" name="paymentType" value="BILLET" />
								
								<c:if test="${order.shoppingCart.quantityOfItems > 0}">
	   				   				<div class="finishOrder">
					    				<input type="submit" class="proccessCartBuy" value="Finalizar compra">
				      	 			</div>
			      	 			</c:if>
				       	 	
				       	 	</form>
					    </div>
					 </div>
					 
						
				</div>
			<input type="hidden" id="refreshed" value="no">
			<script type="text/javascript">
				onload=function() {
					var e=document.getElementById("refreshed");
					if(e.value=="no") {
						e.value="yes";
					} else {
						e.value="no";location.reload();
					}
				};
			</script>
		</div>	
    </body>
</html>