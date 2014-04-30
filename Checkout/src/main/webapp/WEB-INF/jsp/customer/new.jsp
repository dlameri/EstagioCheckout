<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Cadastro de usuário</title>
		<meta charset="utf-8">
		<script src="js/jquery.validationEngine-pt_BR.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/validateForm.js"type="text/javascript"></script>
		<style type="text/css" rel="stylesheet" href="css/validationEngine.jquery.css"></style>
	</head>
	<body>
	<div id="content-container">
		<section class="cadastro">
			<h1 class="title-font">Cadastro de usuário</h1>
			<div class="container">
				<form:form class="registerForm" action="new" autocomplete="off" commandName="register" method="POST">
					<table>
						<tr>
							<td class="label-input"><label>Nome:</label></td>
							<td><form:input type="text" path="customer.name" class="validate[required] text-input" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Sobrenome:</label></td>
							<td><form:input type="text" path="customer.surname" class="validate[required] text-input" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Telefone:</label></td>
							<td><form:input type="text" path="customer.phoneNumber" class="validate[required] phoneNumber"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>CPF:</label></td>
							<td><form:input type="text" path="customer.cpf" class="cpf validate[required] "/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>E-mail:</label></td>
							<td><form:input type="text" path="customer.email" class="validate[required,custom[email]]"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Login:</label></td>
							<td><form:input type="text" path="customer.username" class="validate[required] text-input" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Senha:</label></td>
							<td><form:input type="password"  path="customer.password" id="password" class="validate[required] text-input"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Confirmar Senha:</label></td>
							<td><input type="password" class = "validate[required,equals[password]]" ></td>
						<tr/>
						
						<!-- -- -- -- endereço -- -- -- -->
						
						<tr>
							<td class="label-input"><label>Rua:</label></td>
							<td><form:input type="text" path="address.street" class="validate[required]"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Número:</label></td>
							<td><form:input type="text" path="address.number" class="validate[required]"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Complemento:</label></td>
							<td><form:input type="text" path="address.complement" class="validate[required]"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Cidade</label></td>
							<td><form:input type="text" path="address.city" class="validate[required]"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Estado</label></td>
							<td><form:input type="text" path="address.state" class="validate[required]"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>País</label></td>
							<td><form:input type="text" path="address.country" class="validate[required]"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>CEP</label></td>
							<td><form:input type="text" path="address.zipCode" class="cep validate[required]"/></td>
						<tr/>
					</table>
					<form:button id="submitBtn" class="editAddressButton" type="submit">Cadastrar</form:button>
					<br />
					<br />
					<a class="voltar" href="http://ideaiselectronics.com:8081/Catalogo/">home</a>
			</form:form>
			</div>
		</section>
	</div>
</body>
</html>