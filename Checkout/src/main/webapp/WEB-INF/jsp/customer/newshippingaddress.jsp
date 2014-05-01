<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Editar endereço</title>
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
								<td class="label-input"><label>Rua:</label> </td>
								<td><form:input type="text" path="street" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Número:</label></td>
								<td><form:input type="text" path="number"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Complemento:</label></td>
								<td><form:input type="text" path="complement"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Cidade</label></td>
								<td><form:input type="text" path="city"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Estado</label></td>
								<td><form:input type="text" path="state"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>País</label></td>
								<td><form:input type="text" path="country"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>CEP</label></td>
								<td><form:input type="text" path="zipCode"/></td>
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
						(sendo utilizado)
						Destinatário: ${order.addressee} <br />
						${order.shippingAddress.street}, ${order.shippingAddress.number} <br />
						${order.shippingAddress.neighborhood} ${order.shippingAddress.city},  ${order.shippingAddress.state} - ${order.shippingAddress.zipCode} <br />
						<br /> 	
						<div class="managedAddress">
							<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/editAddressForm/${order.shippingAddress.id}" class="edit">editar</a>
														
							<c:if test="${order.shippingAddress.main != true}">
								<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/removeAddress/${order.shippingAddress.id}" class="edit">excluir</a>	
							</c:if>
						
						</div> 
						<br />	
							
						<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/setShippingAddress/${order.shippingAddress.id}" class="selectAddress">Selecionar endereço</a>
					</div>
					
					<c:forEach items="${notSelectedShippingAddresses}" var="shippingAddress" varStatus="status">
						<div class="shippingAddressListUnit">
							Destinatário: ${order.addressee} <br />
							${shippingAddress.street}, ${shippingAddress.number} <br />
							${shippingAddress.neighborhood} ${shippingAddress.city}, ${shippingAddress.state} - ${shippingAddress.zipCode} <br />
							<br /> 	
							<div class="managedAddress">
								<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/editAddressForm/${shippingAddress.id}" class="edit">editar</a> 
							
								<c:if test="${shippingAddress.main != true}">
									<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/removeAddress/${shippingAddress.id}" class="edit">excluir</a>	
								</c:if>
							
							</div> 
							<br />	
								
							<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/setShippingAddress/${shippingAddress.id}" class="selectAddress">Selecionar endereço</a>
						</div>
					</c:forEach>				
				
				</div>
			
			</section>
			
		</div>

	</body>
</html>