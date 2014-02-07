<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Spring MVC 3 Simples</title>
    </head>
    <body>
		<h1>Itens do carrinho de compras!</h1>
		<ul>
			 <c:forEach items="${list}" var="sku">
                <li>
                	<a href="edit/${sku.id}">${sku.id}</a> - ${sku.name} - ${sku.longDescription}
                	<a href="delete/${sku.id}" >remover</a>
                </li>
            </c:forEach>
		</ul>
		 <h5><a href="new">Novo</a></h5>
    </body>
</html>