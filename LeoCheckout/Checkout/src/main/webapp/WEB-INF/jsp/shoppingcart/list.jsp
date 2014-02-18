<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Spring MVC 3 Simples</title>
    </head>
    <body>
		<h1>Itens do carrinho de compras!</h1>
		<ul>
			 <c:forEach items="${cart.shoppingCartLines}" var="line">
                <li>
					- ${line.item.product.name} - ${line.item.product.longDescription} - quantidade: ${line.quantity} <a href="remove/${line.item.id}" >remover</a>
                </li>
            </c:forEach>
            
            <p> Preço total: ${cart.totalAmount}
            <p> Frete: ${cart.freight}
            <p> total de produtos: ${cart.quantityOfItems}
		</ul>
    </body>
</html>