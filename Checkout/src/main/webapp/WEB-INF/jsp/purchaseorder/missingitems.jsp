<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Loja Virtual - Erro ao concluir a compra</title>
  		
    </head>
    <body>
	    <div id="content-container">
	    
			<h1 class="payment-font">Erro ao concluir a compra</h1>		
				
			<div  id="customerDetails">	
				<span class="successOrder">Os seguintes items infelizmente não se encontram mais no nosso estoque.</span> <br/><br/>
				
				
				<table class="cartItems">
				<thead class="topBox">
					<tr class="cartTableTitles">
						<th class="col01" scope="col" colspan="2">Produto</th>
						<th class="col03" scope="col">Status</th>
						<th class="col04" scope="col">preço</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${missingItems}" var="item" varStatus="status">
					
							<tr class="gridProducts">
								<th rowspan="1">
									<a href="#" class="image">
										<img class="photo" src="${item.imageUrl}" height="90px" />
									</a>
								</th>
								
								<th class="products">
									<div class="product">
										<span id="productTextName" class="cont">
											<strong class="product-name">
												${item.productName} 
											</strong>
										</span>
									</div>
								</th>
								
								<td>
									<div class="noStockMessage">Em falta no estoque</div>
								</td>
				
								<td class="col04">
								   	<div class="valores"> ${item.formattedPriceFor}
								   	</div>
								</td>
	
							</tr>
						
	            	</c:forEach>
						

				</tbody>
					
			
			</table>
			
				
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<div class="managedCustomer">
					<a class="voltar" href="http://ideaiselectronics.com:9082/Checkout/shoppingCart/">Ir para o carrinho de compras sem os items</a>
				</div>
			</div>
			
		</div>	
    </body>
</html>