<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Login</title>
		<meta charset="utf-8">
	</head>
	<body>

	<section class="cadastro">
		<h3 class="title">Cadastro de usuário</h3>

		<form:form class="RegisterForm" action="new" autocomplete="off" commandName="register" method="POST">
			<label>Nome:</label>
			<form:input type="text" path="customer.name" />
			<br/>
			<label>Sobrenome:</label>
			<form:input type="text" path="customer.surname" />
			<br/>
			<label>Telefone:</label>
			<form:input type="text" path="customer.phoneNumber" />
			<br/>
			<label>CPF:</label>
			<form:input type="text" path="customer.cpf" />
			<br/>
			<label>E-mail:</label>
			<form:input type="email" path="customer.email" />
			<br/>
			<label>Login:</label>
			<form:input type="text" class="form-control" path="customer.username" />
			<br/>
			<label>Senha:</label>
			<form:input type="password" class="form-control" path="customer.password" />
			<br/>
			
			<!-- -- -- -- endereço -- -- -- -->
			
			<label>Rua:</label>
			<form:input type="text" path="address.street" />
			<br/>
			<label>numero:</label>
			<form:input type="text" path="address.number"/>
			<br/>
			<label>Complemento:</label>
			<form:input type="text" path="address.complement"/>
			<br/>
			<label>city</label>
			<form:input type="text" path="address.city"/>
			<br/>
			<label>state</label>
			<form:input type="text" path="address.state"/>
			<br/>
			<label>country</label>
			<form:input type="text" path="address.country"/>
			<br/>
			<label>CEP</label>
			<form:input type="text" path="address.zipCode"/>
			<br/>
			<form:button class="button" type="submit">Cadastrar</form:button>
		</form:form>
	</section>

</body>
</html>