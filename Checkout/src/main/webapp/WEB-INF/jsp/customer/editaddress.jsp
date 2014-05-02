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
			<section class="cadastro">
				<h1 class="payment-font">Editar endereço principal</h1>
				
				<div class="container">
					<form:form class="form" action="http://ideaiselectronics.com:9082/Checkout/customer/address/editAddressOrder" autocomplete="off" commandName="address" method="POST" accept-charset="UTF-8">
						<form:hidden path="id"/>
						<form:hidden path="main"/>
						<table>
						
							<c:if test="${main != true}">
								<tr>
									<td class="label-input"><label>Descrição:</label> </td>
									<td><form:input type="text" path="name" class="validate[required] text-input" /></td>
								<tr/>
							</c:if>
						
							<tr>
								<td class="label-input"><label>Rua:</label> </td>
								<td><form:input type="text" path="street" class="validate[required] text-input" /></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Número:</label></td>
								<td><form:input type="text" path="number" class="validate[required] "/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Complemento:</label></td>
								<td><form:input type="text" path="complement"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Cidade</label></td>
								<td><form:input type="text" path="city" class="validate[required] text-input"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>Estado</label></td>
								<td><form:input type="text" path="state" class="validate[required] text-input"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>País</label></td>
								<td><form:input type="text" path="country" class="validate[required] text-input"/></td>
							<tr/>
							<tr>
								<td class="label-input"><label>CEP</label></td>
								<td><form:input type="text" path="zipCode" class="validate[required] cep"/></td>
							<tr/>
						</table>
						
						<form:button class="editAddressButton" type="submit">Editar endereço</form:button>
						<br />
						<br />
						<a class="voltar" href="javascript:history.go(-1)">voltar</a>
					</form:form>
				</div>
			</section>
		</div>

	</body>
</html>