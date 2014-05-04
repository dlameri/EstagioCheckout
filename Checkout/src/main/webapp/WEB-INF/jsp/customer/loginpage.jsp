<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Login</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
		<meta charset="utf-8">
	</head>
	<body>
		<div id="content-container">
			<section class="login">
				<div class="container-login">
					<div class="containerNewAddress">
						<h1 class="title-font">Faça Login</h1>
						
						<c:if test="${errorMessage != null}">
							<div class="errormsgbox">${errorMessage}</div>
						</c:if>
						
						<c:if test="${successMessage != null}">
							<div class="successbox">${successMessage}</div>
						</c:if>
						
						<form class="formLogin" method="post" action="http://ideaiselectronics.com:9082/Checkout/customer/authenticate/loginUser" name="login">
							<table>
								<fieldset class="input-freight">
									<tr>
										<td class="label-input"><label class="loginLabel">Email de usuário</label> </td>
										<td colspan="2"><input type="text" maxlength="100" name="userLogin" id="userLogin" />  </td>
									<tr />
									<tr>	
										<td class="label-input"><label class="passwordLabel">Senha</label></td>
										<td colspan="2"><input type="password" maxlength="100" name="userPassword" id="userLogin" /></td>
									<tr />
							
								</fieldset>
							</table>
							
							<input class="loginButton" type="submit" value="logar" />
						</form>
						<br />
						
						<a href="http://ideaiselectronics.com:9082/Checkout/customer/authenticate/recoverPasswordForm">Esqueci minha senha</a>
					</div>	
					
					<div class="registerLogin">
					<h1 class="title-font">Não sou cadastrado</h1>
						<div class="registerButtonContainer">
							<a class="registerLoginButton" href="http://ideaiselectronics.com:9082/Checkout/customer/new">cadastre-se</a>
						</div>
					</div>
					
					
				</div>
					
			</section>
		</div>        
	</body>
</html>