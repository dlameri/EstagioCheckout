<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
    <script src="js/jquery.validationEngine-pt_BR.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/validateForm.js"type="text/javascript"></script>
		<style type="text/css" rel="stylesheet" href="css/validationEngine.jquery.css"></style>
        <title>Editar usuário</title>
    </head>
    <body>
			<div id="content-container">
			<section class="addressForm">
				<h1 class="payment-font">Editar dados de usuário</h1>
				
				<c:if test="${errorMessage != null}">
					<div class="errormsgbox">${errorMessage}</div>
				</c:if>
				
				<c:if test="${successMessage != null}">
					<div class="successbox">${successMessage}</div>
				</c:if>
				
				<div class="containerEditCustomer">
					<form:form class="formNew" action="http://ideaiselectronics.com:9082/Checkout/customer/updateCustomer" autocomplete="off" commandName="updatedCustomer" method="POST">
						<table>
							<tr>
								<td class="label-input"><label>Nome:</label></td>
								<td><form:input type="text" maxlength="100" path="name" class="validate[required]" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Sobrenome:</label></td>
								<td><form:input type="text" maxlength="100" path="surname"  class="validate[required]" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Telefone:</label></td>
								<td><form:input type="text" maxlength="100" path="phoneNumber"  class="validate[required] phoneNumber"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Login:</label></td>
								<td><form:input type="text" maxlength="100" path="username"  class="validate[required]"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Senha:</label></td>
								<td><form:input type="password" maxlength="100" path="password"  class="validate[required]"/></td>
							<tr/>
						</table>
						<form:button class="editAddressButton" maxlength="100" type="submit">Editar</form:button>
						<br />
						<br />
						<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/newCustomerAddress" class="selectAddress">Cadastrar novo endereço</a>
						
						<br />
						<br />
						<div class="managedAddress">
							<a class="voltar" href="http://ideaiselectronics.com:9082/Checkout/customer/customerDetails">Voltar para minha conta</a>
						</div>
				</form:form>
				</div>
				
				<div class="shippingAddressList">
				
					<c:forEach items="${customer.deliveryAddresses}" var="shippingAddress" varStatus="status">
						<div class="shippingAddressListUnit">
						    <span class="orderInfo">${shippingAddress.name}</span> <br />
							<c:if test="${shippingAddress.main == true}">
						    	(Endereço padrão)
						    </c:if>Destinatário: ${shippingAddress.addressee} <br />
							${shippingAddress.street}, ${shippingAddress.number} <br />
							${shippingAddress.neighborhood} ${shippingAddress.city}, ${shippingAddress.state} - ${shippingAddress.zipCode} <br />
							<br /> 	
							<div class="managedAddress">
								<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/editAddressForm/${shippingAddress.id}" class="edit">editar</a> 
							
								<c:if test="${shippingAddress.main != true}">
									<a id="opener" href="javascript:void(0)" class="edit" onclick = "summonLightBox('http://ideaiselectronics.com:9082/Checkout/customer/address/removeAddress/${shippingAddress.id}')">excluir</a>
								</c:if>
							
							</div> 
							<br />	
								
							<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/setMainAddress/${shippingAddress.id}" class="selectAddress">Tornar como endereço padrão</a>
						
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