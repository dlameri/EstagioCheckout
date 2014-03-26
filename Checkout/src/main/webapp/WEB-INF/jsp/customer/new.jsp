<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Spring MVC 3 Simples</title>
    </head>
    <body>
		<h1>Novo cliente</h1>
        <form:form action="new" method="post" commandName="customer">
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