<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Editar usuário</title>
    </head>
    <body>
			<div id="content-container">
			<section class="addressForm">
				<h1 class="payment-font">Editar dados de usuário</h1>
				
				<div class="containerEditCustomer">
					<form:form class="formNew" action="updateCustomer" autocomplete="off" commandName="customer" method="POST">
						<table>
							<tr>
								<td class="label-input"><label>Nome:</label></td>
								<td><form:input type="text" path="name" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Sobrenome:</label></td>
								<td><form:input type="text" path="surname" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Telefone:</label></td>
								<td><form:input type="text" path="phoneNumber" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>CPF:</label></td>
								<td><form:input type="text" path="cpf" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>E-mail:</label></td>
								<td><form:input type="email" path="email" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Login:</label></td>
								<td><form:input type="text" class="form-control" path="username" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Senha:</label></td>
								<td><form:input type="password" class="form-control" path="password" /></td>
							<tr/>
						</table>
						<form:button class="editAddressButton" type="submit">Editar</form:button>
						<br />
						<br />
						<a class="voltar" href="http://ideaiselectronics.com:9082/Checkout/customer/customerDetails">Voltar para minha conta</a>
				</form:form>
				</div>
				
				<div class="shippingAddressList">
				
					<c:forEach items="${customer.deliveryAddresses}" var="shippingAddress" varStatus="status">
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
								
							<a href="http://ideaiselectronics.com:9082/Checkout/customer/address/setMainAddress/${shippingAddress.id}" class="selectAddress">Tornar como endereço principal</a>
						</div>
					</c:forEach>	
				</div>			
			
			</section>
			
		</div>

    </body>
</html>