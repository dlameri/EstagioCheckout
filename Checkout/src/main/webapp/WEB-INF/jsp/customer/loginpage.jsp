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
					
						<form class="formLogin" method="post" action="loginUser" name="login">
							<table>
								<fieldset class="input-freight">
									<tr>
										<td class="label-input"><label class="loginLabel">Email ou login de usuário</label> </td>
										<td><input type="text" name="userLogin" id="userLogin" />  </td>
									<tr />
									<tr>	
										<td class="label-input"><label class="passwordLabel">Senha</label></td>
										<td><input type="password" name="userPassword" id="userLogin" /></td>
									<tr />
							
								</fieldset>
							</table>
							
							<input class="loginButton" type="submit" value="logar" />
						</form>
					</div>	
					
					<div class="registerLogin">
					<h1 class="title-font">Não sou cadastrado</h1>
						<div class="registerButtonContainer">
							<a class="registerLoginButton" href="">cadastre-se</a>
						</div>
					</div>
					
					
				</div>
					
			</section>
		</div>        
	</body>
</html>