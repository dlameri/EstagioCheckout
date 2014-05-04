<%@page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Loja Virtual - Compra realizada com sucesso</title>
  		
    </head>
    <body>
	    <div id="content-container">
	    
			<h1 class="payment-font">Pagamento</h1>		
				
			<div  id="customerDetails">	
				<span class="successOrder">${order.customer.username} sua compra foi realizada com sucesso! Um email foi enviado para ${order.customer.email} com os detalhes da compra.</span>
				
				<br />
				<br />
				<a class="voltar" href="http://ideaiselectronics.com:8081/catalogo/">home</a>
			</div>
			
		</div>	
    </body>
</html>