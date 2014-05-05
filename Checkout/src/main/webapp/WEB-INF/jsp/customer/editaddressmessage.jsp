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
				
				<c:if test="${errorMessage != null}">
					<div class="errormsgbox">${errorMessage}</div>
				</c:if>
				
				<c:if test="${successMessage != null}">
					<div class="successbox">${successMessage}</div>
				</c:if>
				
				<c:if test="${address != null}">
					<div class="container">
						<form:form class="form" action="http://ideaiselectronics.com:9082/Checkout/customer/address/editAddress" autocomplete="off" commandName="address" method="POST" accept-charset="UTF-8">
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
									<td><form:input type="text" maxlength="100"  path="reference" class="validate[required] text-input"/></td>
								<tr/>
								<tr>
									<td class="label-input"><label>Estado</label></td>
									<td><form:input type="text" maxlength="100" path="state" class="validate[required] text-input"/></td>
								<tr/>
								<tr>
									<td class="label-input"><label>País</label></td>
									<td><form:input type="hidden" value="Brasil" maxlength="100" path="country" class="validate[required] text-input"/></td>
								<tr/>
								<tr>
									<td class="label-input"><label>CEP</label></td>
									<td><form:input type="text" maxlength="100" path="zipCode" class="validate[required] cep"/></td>
								<tr/>
							</table>
							
							<form:button class="editAddressButton" type="submit">Editar endereço</form:button>
							<br />
							<br />
							<a class="voltar" href="javascript:history.go(-2)">voltar</a>
						</form:form>
					</div>
				</c:if>
			</section>
		</div>

	</body>
</html>