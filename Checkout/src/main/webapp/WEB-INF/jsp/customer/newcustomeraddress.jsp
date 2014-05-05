<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Cadastro de endereço</title>
		<meta charset="utf-8">
		<script src="js/jquery.validationEngine-pt_BR.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/validateForm.js"type="text/javascript"></script>
		<style type="text/css" rel="stylesheet" href="css/validationEngine.jquery.css"></style>
	</head>
	<body>
	<div id="content-container">
		<section class="cadastro">
			<h1 class="title-font">Cadastro de endereço</h1>
			
			<c:if test="${errorMessage != null}">
				<div class="errormsgbox">${errorMessage}</div>
			</c:if>
			
			<div class="container">
				<form:form class="formNew" action="newAddressCustomerDetails" autocomplete="off" commandName="address" method="POST" accept-charset="UTF-8">
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
						
						<form:button class="editAddressButton" type="submit">Cadastrar endereço</form:button>
						<br />
						<br />
						<a class="voltar" href="http://ideaiselectronics.com:9082/Checkout/customer/edit">voltar para minhas informações</a>
					</form:form>
			</div>
		</section>
	</div>
</body>
</html>