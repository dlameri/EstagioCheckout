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
				
				<div class="box1" class="payment-font">
					
					<div class="sub-box1">
						<p class="payment-subtitle">endereço de entrega</p>
						<div class="shipping-address">
							Destinatário: ${order.addressee} <br />
							${order.shippingAddress.street}, ${order.shippingAddress.number}, ${order.shippingAddress.city}, ${order.shippingAddress.state} - ${order.shippingAddress.zipCode} <br />
							<a class="editShipAddress" href="../customer/address/newAddress"><span class="editAddressShipping">alterar o endereço de entrega</span></a>
							<a href="../customer/address/editAddressOrderForm/${order.shippingAddress.id}"><span class="editAddressShipping" class="right-values">Editar o endereço atual</span></a>
						</div>
					</div>
					
					<div class="sub-box2">
						<p class="payment-subtitle">dados de compra</p>
						<div class="payment-data">
							Total em produtos (${order.shoppingCart.formattedQuantityOfItems}) <span class="right-values">${order.shoppingCart.formattedSubTotalAmount}</span><br />
							Frete para ${order.shippingAddress.city}<span class="right-values">${order.shoppingCart.formattedFreight}</span> <br />
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
									<a id="creditSelect" href="#" class="selectPayment">Selecionar</a>			      			
								</li>
			      			</div>
					      
							<div class="tab">
						     	<li>
						     		<div class="payment-type">
						      			<img class="photo" src="<c:url value="/resources/images/boleto_image.jpg" />" />
									</div>
									<a id="billetSelect" href=#"" class="selectPayment">Selecionar</a>
				      			</li>
			      			</div>
  
					    </ul>
					    
					    <div id="formCard" class="simpleTabsContent">

					    	<div>
								<div>
									<div>			
									<ul>							
										
										<li>
											<label><span>Nome do titular:</span>
											<input type="text" name="holderName" autocomplete="off"  maxlength="100" />
											</label>
											<span class="holderNameAdvise">(Como gravado no cartão)</span>
										</li>
										<li>
											<label><span>Número do cartão:</span>
												<input type="text" name="cardNumber" autocomplete="off" maxlength="19"  />	
											</label>
										</li>
										<li id="errorDateLi">
											<div id="errorDate" style="display: none;" class="payErCont"></div>
											
											<label>						
												<span>Validade:</span><select name="month" size="1">	
												<option value="" selected="selected">Mês</option>
													<option value="01">01</option>
													<option value="02">02</option>
													<option value="03">03</option>
													<option value="04">04</option>
													<option value="05">05</option>
													<option value="06">06</option>
													<option value="07">07</option>
													<option value="08">08</option>
													<option value="09">09</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
												</select>
											</label>
										 	<label><select id="year" name="year" size="1">	<option value="" selected="selected">Ano</option>
													<option value="2014">2014</option>
													<option value="2015">2015</option>
													<option value="2016">2016</option>
													<option value="2017">2017</option>
													<option value="2018">2018</option>
													<option value="2019">2019</option>
													<option value="2020">2020</option>
													<option value="2021">2021</option>
													<option value="2022">2022</option>
													<option value="2023">2023</option>
													<option value="2024">2024</option>
												</select><span class="validationError"></span>
											</label>
										</li>
										<li>
											<div></div><span>							
												<span>Código de segurança do Cartão:</span>
												<input id="securityCode" type="text" name="securityCode" autocomplete="off" maxlength="4" />
										</li>
										
										<li id=installmentsLi">
											<div id="installmentErrorMessage" style="display: none;" class="payErCont"></div>
											<label><span class="lbl">Parcelar em:</span><input id="acomForm:installmentQuantity" type="text" name="acomForm:installmentQuantity" autocomplete="off" class="tp3" maxlength="2" />
											</label>		
											<label><span>
													<select id="installmentOptions">
													</select><span id="acomForm:installmentQuantityMessage" class="validationError"></span></span>
											</label><input id="acomForm:installmentOptionChosen" type="hidden" name="acomForm:installmentOptionChosen" />
											
										</li>
									</ul></div>
								</div>
				
							</div>
			
					
					    </div>
					    <div id="formBillet" class="simpleTabsContent">

							<p>
								Formulário de boleto
							</p>

					    </div>
					 </div>
					 
				<div class="finishOrder">
      				<a class="proccessCartBuy" href="processOrder">Finalizar compra</a>
	       	 	</div>
						
				</div>
			
		</div>	
    </body>
</html>