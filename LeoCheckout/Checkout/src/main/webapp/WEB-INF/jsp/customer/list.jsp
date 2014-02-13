<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Spring MVC 3 Simples</title>
    </head>
    <body>
		<h1>Clientes!</h1>
		<ul>
			 <c:forEach items="${list}" var="pizza">
                <li>
                	<a href="edit/${customer.id}">${customer.id}</a> - ${customer.name}
                	<a href="delete/${customer.id}" >remover</a>
                </li>
            </c:forEach>
		</ul>
		 <h5><a href="new">Novo</a></h5>
    </body>
</html>