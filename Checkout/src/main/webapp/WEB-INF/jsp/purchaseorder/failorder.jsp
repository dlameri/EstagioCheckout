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
				<span class="successOrder">${errorMessage}</span>
				
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<div class="managedCustomer">
					<a class="voltar" href="http://ideaiselectronics.com:8081/catalogo/">Continuar comprando</a>
				</div>
			</div>
			
		</div>	
    </body>
</html>