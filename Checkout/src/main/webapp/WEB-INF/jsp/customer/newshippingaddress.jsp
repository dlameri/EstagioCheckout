<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Editar endereço</title>
		<script src="js/jquery.validationEngine-pt_BR.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/validateForm.js"type="text/javascript"></script>
		<style type="text/css" rel="stylesheet" href="css/validationEngine.jquery.css"></style>
		
		<meta charset="utf-8">
	</head>
	<body>
	    <div id="content-container">
			<section class="addressForm">
				<h1 class="payment-font">Cadastrar novo endereço de entrega</h1>
				
				<div class="containerNewAddress">
					<form:form class="formNew" action="newShippingAddress" autocomplete="off" commandName="address" method="POST" accept-charset="UTF-8">
						<form:hidden path="id"/>
						<table>
							<tr>
								<td class="label-input"><label>Descrição:</label> </td>
								<td><form:input type="text" maxlength="100" path="name" class="validate[required] text-input" /></td>
							<tr/>						
							<tr>
								<td class="label-input"><label>Destinatário:</label> </td>
								<td><form:input type="text" maxlength="100" path="addressee" class="validate[required] text-input" /></td>
							<tr/>	
							<tr>
								<td class="label-input"><label>Rua:</label> </td>
								<td><form:input type="text" maxlength="100" path="street" class="validate[required] text-input" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Número:</label></td>
								<td><form:input type="text" maxlength="100" path="number" class="validate[required] "/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Complemento:</label></td>
								<td><form:input type="text" maxlength="100" path="complement"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Cidade</label></td>
								<td><form:input type="text" maxlength="100" path="city" class="validate[required] text-input"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Bairro</label></td>
								<td><form:input type="text" maxlength="100" path="neighborhood" class="validate[required] text-input"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Referencia</label></td>
								<td><form:input type="text" path="reference" class="validate[required] text-input"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Estado</label></td>
								<td><form:input type="text" maxlength="100" path="state" class="validate[required] text-input"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>País</label></td>
								<td><form:input type="text" maxlength="100" path="country" class="validate[required] text-input"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>CEP</label></td>
								<td><form:input type="text" maxlength="100" path="zipCode" class="validate[required] cep"/></td>
							<tr/>
						</table>
						
						<form:button class="editAddressButton" type="submit">Cadastrar endereço de entrega</form:button>
						<br />
						<br />
						<a class="voltar" href="http://ideaiselectronics.com:9082/Checkout/purchaseOrder/paymentDetails">voltar para minha compra</a>
					</form:form>
				</div>
			
				<div class="shippingAddressList">
				
					<div class="shippingAddressListUnit">
						<span class="orderInfo">${order.shippingAddress.name}</span> <br />
						(Sendo utilizado)
						Destinatário: ${order.addressee} <br />
						${order.shippingAddress.street}, ${order.shippingAddress.number} <br />
						${order.shippingAddress.neighborhood} ${order.shippingAddress.city},  ${order.shippingAddress.state} - ${order.shippingAddress.zipCode} <br />
						<br /> 	
						<div class="managedAddress">
							<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/editAddressForm/${order.shippingAddress.id}" class="edit">editar</a> 
						
							<c:if test="${order.shippingAddress.main != true}">
								<a id="opener" href="javascript:void(0)" class="edit" onclick = "summonLightBox('http://ideaiselectronics.com:9082/Checkout/customer/address/removeAddress/${shippingAddress.id}')">excluir</a>
							</c:if>
						</div> 
						<br />	
							
						<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/setShippingAddress/${order.shippingAddress.id}" class="selectAddress">Selecionar endereço</a>
					</div>
					
					<c:forEach items="${notSelectedShippingAddresses}" var="shippingAddress" varStatus="status">
						<div class="shippingAddressListUnit">
						    <span class="orderInfo">${shippingAddress.name}</span> <br />
							Destinatário: ${shippingAddress.addressee} <br />
							${shippingAddress.street}, ${shippingAddress.number} <br />
							${shippingAddress.neighborhood} ${shippingAddress.city}, ${shippingAddress.state} - ${shippingAddress.zipCode} <br />
							<br /> 	
							<div class="managedAddress">
								<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/editAddressForm/${shippingAddress.id}" class="edit">editar</a> 

								<c:if test="${shippingAddress.main != true}">
									<a href="javascript:void(0)" class="edit" onclick = "summonLightBox('http://ideaiselectronics.com:9082/Checkout/customer/address/removeAddress/${shippingAddress.id}')">excluir</a>	
								</c:if>
							
							</div> 
							<br />	
								
							<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/setShippingAddress/${shippingAddress.id}" class="selectAddress">Selecionar endereço</a>
						
						</div>
					</c:forEach>				
				
				</div>
			
			</section>
			
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
 		<div id="light" class="white_content hidden"></div>
			<div id="fade" class="black_overlay hidden"></div>
	</body>
</html>