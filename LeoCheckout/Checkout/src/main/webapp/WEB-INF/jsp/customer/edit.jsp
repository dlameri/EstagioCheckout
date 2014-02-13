<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Spring MVC 3 Simples</title>
    </head>
    <body>
		<h1>Editar Cliente</h1>
        <form:form action="../edit" method="post" commandName="customer">
        	<form:hidden path="id"/>
            <table>
                <tr>
                    <td>Nome: <form:input path="name" /></td>
                    <td>Descrição: <form:input path="description" /></td>
                    <td><input type="submit"></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>