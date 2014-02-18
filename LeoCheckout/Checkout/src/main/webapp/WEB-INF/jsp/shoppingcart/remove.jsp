<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Spring MVC 3 Simples</title>
    </head>
    <body>
		<h1>Remover Item</h1>
        <form:form action="../remove" method="post" commandName="item">
        	<form:hidden path="id"/>
            <h2>Tem certeza que dezeja remover o item :<span>${item.product.name}</span></h2>
			<input type="submit">
        </form:form>
    </body>
</html>