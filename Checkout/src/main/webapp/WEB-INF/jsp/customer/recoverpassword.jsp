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
					<div class="containerRecover">
						<h1 class="title-font">Recupere a sua senha</h1>
						
						<c:if test="${errorMessage != null}">
							<div class="errormsgbox">${errorMessage}</div>
						</c:if>
						
						<c:if test="${successMessage != null}">
							<div class="successbox">${successMessage}</div>
						</c:if>
						
						<span>Informe os dados abaixo, você receberá um email com a sua senha recuperada.</span>
						
						<form class="formLogin" method="post" action="http://ideaiselectronics.com:9082/Checkout/customer/authenticate/recoverPassword" name="recoverPassword">
							<table>
								<fieldset class="input-freight">
									<tr>
										<td class="label-input"><label class="loginLabel">Email</label> </td>
										<td><input type="text" maxlength="100" name="userEmail" id="userEmail" />  </td>
									<tr />
									<tr>	
										<td class="label-input"><label class="passwordLabel">Login de usuário</label></td>
										<td><input type="text" maxlength="100" name="userLogin" id="userLogin" /></td>
									<tr />
							
								</fieldset>
							</table>
							
							<input class="loginButton" type="submit" value="Recuperar" />
						</form>
						<br />
						<br />
						<a class="voltar" href="javascript:history.go(-1)">voltar</a>
					</div>
					
					
				</div>
					
			</section>
		</div>        
	</body>
</html>