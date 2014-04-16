<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Login</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
		<script type="text/javascript" src="<c:url value="/resources/js/facebook-login.js" />" > </script>
		<meta charset="utf-8">
	</head>
	<body>
		<div id="wrapper">		
			<header id="main-header">
				<div class="container-header">
					<h1 class="logo"><a href="${pageContext.request.contextPath}">Ideais Electronics</a></h1>
					
					<div id="search-area">
						<form method="get" action="${pageContext.request.contextPath}/product/search" name="searchProduct">
							<div class="search-box">
								<input type="text" name="name" id="input-search-text">
								<input type="submit" value="Pesquisar" id="input-search-submit" class="search-icon">
							</div>
						</form>
					</div>
					
					<div class="login-or-register">
						<span>Olá, visitante!</span>
						<a href="">Cadastre-se</a>
						<a href="">Entre</a>
					</div>
		
					<div class="cart">
						<span class="shopping-cart">Carrinho</span>
						<span class="qtCart">0</span>
					</div>
		
					<nav id="main-nav" class="main-menu">
						<ul class="menu">
							<c:forEach items="${categories}"  var="category">
								<li class="menu-item"><a href="${pageContext.request.contextPath}/category/${category.id}/product">${category.name}</a>
									<ul class="sub-menu">
										<c:forEach items="${category.subcategories}" var="subcategory">
											<li class="submenu-item"><a href="${pageContext.request.contextPath}/category/subcategory/${subcategory.id}/product">${subcategory.name}</a></li>
										</c:forEach>
									</ul>
								</li>
							</c:forEach>
						</ul>
					</nav>		
				</div>
			</header>	
			
			<div id="content">
				<div id="content-container">
					<section class="login">
	    				<h3 class="title">Faça login com o facebook</h3>
						<fb:login-button class="facebook-button" show-faces="true" width="200" max-rows="1"></fb:login-button>
	
	    				<h3 class="title">Faça seu Login sem o facebook</h3>
						<form class="loginForm" action="" autocomplete="off" method="POST">
							<div class="input-group">
								<input type="text" class="form-control" name="username" placeholder="email address">
							</div>
							<span class="help-block"></span>
							
							<form:form action="emptyCart" method="post" commandName="cart">
					            <span>esvaziar carrinho de compras</span>
								<input class="cartItemInput" value="esvaziar" type="submit">
					        </form:form>
							
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input  type="password" class="form-control" name="password" placeholder="Password">
							</div>
							<button class="login-button" type="submit">Login</button>
						</form>
	
					</section>
				</div>        
			</div><!-- #content -->
			<c:import url="/WEB-INF/jsp/footer.jsp"/>
			</div><!-- #wrapper -->
	</body>
</html>