<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Login</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
		<meta charset="utf-8">
	</head>
	<body>
		<div id="wrapper">	
			<div id="header">
				<div id="line"></div>
				<div id="header-content">
					<figure>
						<a href="#"><img src="<c:url value="/resources/images/logo.png" />" alt="imagem do produto x"/></a> 
					</figure>
					<h2 class="logo-title">Loja Virtual</h2>
					<h5 class="logo-subtitle">Breve descrição da loja</h5> 
					<ul>
						<li><a href="#">Home</a></li>
						<li><a href="#">Categorias</a>
							<ul>
								<li><a href="#">Categoria 1</a></li>
								<li><a href="#">Categoria 2</a></li>
								<li><a href="#">Categoria 3</a></li>
								<li><a href="#">Categoria 4</a></li>
							</ul> 
						</li>
						<li><a href="#">Sobre</a></li>
						<li><a href="#">Fale Conosco</a></li>
					</ul>		
				</div>			
			</div><!-- #header -->		
			<div id="content">
				<div id="content-container">
					<section class="login">
	    				<h3 class="title">Faça seu Login</h3>
					    <form class="loginForm" action="" autocomplete="off" method="POST">
							<div class="input-group">
								<input type="text" class="form-control" name="username" placeholder="email address">
							</div>
							<span class="help-block"></span>
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input  type="password" class="form-control" name="password" placeholder="Password">
							</div>
							<button class="facebook-button" type="submit">Login com Facebook</button>
						</form>
					</section>
				</div>        
			</div><!-- #content -->
			<div id="footer">
				<div id="footer-content">
					<a href="#"> Powered by Estagiários </a>
				</div>
			</div><!-- #footer -->
			</div><!-- #wrapper -->
	</body>
</html>