<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - carrinho de compras</title>
		<link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
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
					
					<p> ${error}</p>
			        
			        <div class="freightBox">
						<p class="freight-calcule">consulte o prazo de entrega do seu pedido</p>
						<div class="calc">
							
							<form method="post" action="calculateFreight" name="calculateFreight">
								<fieldset class="inpPostCd">
							
									<label class="cep">Digite seu CEP:</label>
									<input type="text" name="postalCodeID1" id="postalCodeID1" maxlength="5" class="tp1"/>   
									<input type="text" name="postalCodeID2" id="postalCodeID2" maxlength="3" class="tp2"/>
									
									<input class="calculateFreight" type="submit" value="calcular frete" />
									<a id="searchCep" href="#">
										Procurar CEP
									</a>
									
								</fieldset>
							</form>
							
						</div> 
		 			</div>
	
	
					<table class="cartItems">
						<thead class="topBox">
							<tr>
								<th class="col01" scope="col" colspan="2">Produto</th>
								<th class="col02" scope="col">Quantidade</th>
								<th class="col03" scope="col">Entrega</th>
								<th class="col04" scope="col">Valor Unitário</th>
								<th class="col05" scope="col">Valor Total</th>
							</tr>
						</thead>
						
						<tbody>
							<c:forEach items="${cart.shoppingCartLines}" var="line" varStatus="status">
								<form method="post" action="editQuantity/${line.item.id}" name="editQuantity">
							
									<tr class="gridProducts">
										<th rowspan="1">
											<a href="#" class="image">
												<img class="photo" src="#" />
											</a>
										</th>
										
										<th class="products">
											<div class="product">
												<a href="#" class="productName">
													<span class="cont">
														<strong class="n name">${line.item.product.name}</strong><br />
													</span>
												</a>
											</div>
										</th>
										
										<td rowspan="1" class="col02">
										
												<input class="quantityInput" type="text" value="${line.quantity}" name="quantity" maxlength="2" />
												
												<div class="input">
													<input class="cartItemInput" type="submit" value="editar quantidade" />
												</div>
												<div class="input">
													<a class="cartItemInput" href="remove/${line.item.id}" >remover</a>
									    		</div>
										
										<td rowspan="1" class="col03">
											<div class="entrega">${sessionScope.freightDetails.deliveryDays}</div>
										</td>	
						
										<td class="col04">
										   	<div class="valores"> ${line.item.formattedPriceFor}
										   	</div>
										</td>
										
										<td class="col05" >
											<div class="valores"> ${line.formattedPrice}</div>
										</td>
			
									</tr>
								</form>
								
			            	</c:forEach>
								
	
						</tbody>
							
						<tfoot class="cartTotal" class="valores">
							<tr class="cartTotal" class="valores">
								<th colspan="5" scope="row">SUBTOTAL:</th>
								<td>
									<span> ${cart.formattedSubTotalAmount}</span>			
								</td>
							</tr>
								
								
							<tr class="cartTotal" class="valores">
								<td colspan="4"><span class="ico"></span></td><th class="sp tp1">FRETE:</th>
								<td>
									<span> ${cart.formattedFreight}</span>
								</td>
							</tr>
							
							<tr class="cartTotal" class="valores">
								
								<th scope="row" colspan="5">TOTAL DE PRODUTOS:</th>
								<td>
									<span>${cart.quantityOfItems}</span>
								</td>
							</tr>
	
							<tr class="totalAmount" class="valores">
								
								<th scope="row" colspan="5">TOTAL:</th>
								<td>
									<span> ${cart.formattedTotalAmount}</span>
								</td>
							</tr>
						
						</tfoot>
					</table>
					
			        <form:form action="emptyCart" method="post" commandName="cart">
		        		<form:hidden path="id"/>
			            <span>esvaziar carrinho de compras</span>
						<input class="cartItemInput" value="esvaziar" type="submit">
			        </form:form>
			        
        		 	<h2><a href="proccessShoppingCart">Comprar</a></h2>
			        
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