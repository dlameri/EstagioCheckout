<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Cadastro de usuário</title>
		<meta charset="utf-8">
	</head>
	<body>
	<div id="content-container">
		<section class="cadastro">
			<h1 class="title-font">Cadastro de usuário</h3>
			<div class="container">
				<form:form class="form" action="new" autocomplete="off" commandName="register" method="POST">
					<table>
						<tr>
							<td class="label-input"><label>Nome:</label></td>
							<td><form:input type="text" path="customer.name" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Sobrenome:</label></td>
							<td><form:input type="text" path="customer.surname" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Telefone:</label></td>
							<td><form:input type="text" path="customer.phoneNumber" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>CPF:</label></td>
							<td><form:input type="text" path="customer.cpf" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>E-mail:</label></td>
							<td><form:input type="email" path="customer.email" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Login:</label></td>
							<td><form:input type="text" class="form-control" path="customer.username" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Senha:</label></td>
							<td><form:input type="password" class="form-control" path="customer.password" /></td>
						<tr/>
						
						<!-- -- -- -- endereço -- -- -- -->
						
						<tr>
							<td class="label-input"><label>Rua:</label></td>
							<td><form:input type="text" path="address.street" /></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Número:</label></td>
							<td><form:input type="text" path="address.number"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Complemento:</label></td>
							<td><form:input type="text" path="address.complement"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Cidade</label></td>
							<td><form:input type="text" path="address.city"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>Estado</label></td>
							<td><form:input type="text" path="address.state"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>País</label></td>
							<td><form:input type="text" path="address.country"/></td>
						<tr/>
						<tr>
							<td class="label-input"><label>CEP</label></td>
							<td><form:input type="text" path="address.zipCode"/></td>
						<tr/>
					</table>
					<form:button class="editAddressButton" type="submit">Cadastrar</form:button>
					<br />
					<br />
					<a class="voltar" href="http://ideaiselectronics.com:8081/Catalogo/">home</a>
			</form:form>
			</div>
		</section>
	</div>
</body>
</html>