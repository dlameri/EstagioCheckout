<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
		<title>Loja Virtual - Carrinho de Compras</title>
	</head>
	<body>
	
		<div id="content-container">
				        
	        <div class="freightBox">

				<div class="inpPostCd">
				
					<p class="freight-calcule">Consulte o valor e o prazo de entrega do seu pedido</p>

					<form method="post" action="calculateFreight" name="calculateFreight">
						<fieldset class="input-freight">
							
							<label class="cep">Digite seu CEP:</label>
							<input type="text" name="postalCodeID1" id="postalCodeID1" maxlength="5" class="tp1"/>   
							<input type="text" name="postalCodeID2" id="postalCodeID2" maxlength="3" class="tp2"/>
							
							<input class="calculateFreight" type="submit" value="calcular frete" />

							
						</fieldset>
					</form>
					
				</div> 
 			</div>
 			
			<c:if test="${errorMessage != null}">
				<div class="errormsgbox">${errorMessage}</div>
			</c:if>

			<table class="cartItems">
				<thead class="topBox">
					<tr class="cartTableTitles">
						<th class="col01" scope="col" colspan="2">Produto</th>
						<th class="col02" scope="col">Quantidade</th>
						<th class="col03" scope="col">Entrega</th>
						<th class="col04" scope="col">Valor Unitário</th>
						<th class="col05" scope="col">Valor Total</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${cart.shoppingCartLines}" var="line" varStatus="status">
						<form method="post" action="http://ideaiselectronics.com:9082/Checkout/shoppingCart/editQuantity/${line.item.itemId}" name="editQuantity">
					
							<tr class="gridProducts">
								<th rowspan="1">
									<a href="#" class="image">
										<img class="photo" src="${line.item.imageUrl}" height="90px" />
									</a>
								</th>
								
								<th class="products">
									<div class="product">
										<span id="productTextName" class="cont">
											<strong class="product-name">
												${line.item.productName} 
											</strong>
										</span>
									</div>
								</th>
								
								<td rowspan="1" class="col02">
								
										<input class="quantityInput" type="text" value="${line.quantity}" name="quantity" maxlength="2" />
										
										<div class="input" class="edit-item">
											<input class="cartItemInput" type="submit" value="editar quantidade" />
										</div>
										<div class="input">
											<a class="remove-item" class="cartItemInput" href="remove/${line.item.itemId}" >remover</a>
							    		</div>
							   	</td>
								
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
						
						<td>
					        <form:form action="emptyCart" method="post" commandName="cart">
				        		<form:hidden path="id"/>
								<input class="cartEmpty" value="Esvaziar carrinho" type="submit">
					        </form:form>
						</td>
						
						<th scope="row" colspan="4">TOTAL:</th>
						<td>
							<span> ${cart.formattedTotalAmount}</span>
						</td>
					</tr>
				
				</tfoot>
			</table>
			
	        <div class="buyToOrder">
      			<a class="proccessCartBuy" href="proccessShoppingCart">Comprar</a>
	        </div>
	        
		</div><!-- #content -->
		
	</body>

</html>