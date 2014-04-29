<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Spring MVC 3 Simples</title>
    </head>
    <body>
		<h1>Ordem de compras!</h1>
		<ul>
			 <c:forEach items="${list}" var="order">
                <li>
                	<a href="edit/${order.id}">${order.id}</a> - ${order.name}
                	<a href="delete/${order.id}" >remover</a>
                </li>
            </c:forEach>
		</ul>
		 <h5><a href="new">Novo</a></h5>
    </body>
</html>