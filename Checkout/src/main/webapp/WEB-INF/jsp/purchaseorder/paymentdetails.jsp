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
									<a id="billetSelect" href="#" class="selectPayment">Selecionar</a>
				      			</li>
			      			</div>
  
					    </ul>
					    
					    <div id="formCard" class="simpleTabsContent">

					    	<div class="boxWrap arowBox">
								<div class="box1-1"><div id="acomForm:validationErrors" class="box01 cData">			
									<ul class="cardData">							
										
										<li id="acomForm:holderNameLi">
											<div id="acomForm:holderNameError" style="display: none;" class="payErCont"></div>
											<label><span class="lbl">Nome do titular:</span><input id="acomForm:holderName" type="text" name="acomForm:holderName" autocomplete="off" class="tp1" maxlength="100" />
											</label>
											<span>(Como gravado no cartão)</span>
										</li>
										<li id="acomForm:cardNumberLi">
											<div id="acomForm:cardNumberError" style="display: none;" class="payErCont"></div>
											<label><span class="lbl">Número do cartão:</span><input id="acomForm:cardNumber" type="text" name="acomForm:cardNumber" autocomplete="off" class="tp1" maxlength="19" onblur="return onlyNumbers(this)" onkeyup="return onlyNumbers(this)" />	
												<span class="ico lock">-</span>
											</label>
										</li>
										<li id="acomForm:errorDateLi">
											<div id="acomForm:errorDate" style="display: none;" class="payErCont"></div>
											
											<label>						
												<span class="lbl">Validade:</span><select id="acomForm:month" name="acomForm:month" class="tp2 " size="1">	
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
										 	<label><select id="acomForm:year" name="acomForm:year" class="tp2" size="1">	<option value="" selected="selected">Ano</option>
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
										<li id="acomForm:securityCodeLi">
											<div id="acomForm:securityCodeError" style="display: none;" class="payErCont"></div><span id="acomForm:securityPanel">							
												<span class="lbl">Código de segurança do Cartão:</span><input id="acomForm:securityCode" type="text" name="acomForm:securityCode" autocomplete="off" class="tp2" maxlength="4" onkeyup="return onlyNumbers(this)" /><a id="acomForm:codigoSeguranca" name="acomForm:codigoSeguranca" href="/checkout/staticContentPopup.xhtml?articleId=215786" class="lightbox">
											    	O que é código de segurança?
											   		</a></span>	
										</li>
										
										<li id="acomForm:installmentsLi" style="parcel">
											<div id="acomForm:installmentErrorMessage" style="display: none;" class="payErCont"></div>
											<label><span class="lbl">Parcelar em:</span><input id="acomForm:installmentQuantity" type="text" name="acomForm:installmentQuantity" autocomplete="off" class="tp3" maxlength="2" />
											</label>		
											<label><span id="acomForm:installmentPanel">
													<select id="acomForm:installmentOptions" class="paymentSelectInstallments">
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
						
				</div>
			
		</div>	
    </body>
</html>