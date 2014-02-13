<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
    <head>
        <title>Spring MVC 3 Simples</title>
        
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script type="text/javascript" >
// 			$(document).ready(function(){
// 			  sendAjax();
// 			});
			 
			var jsonData;
			 
			function checkout() {
				
// 				$.getJSON('http://localhost:9000/MockedShoppingCart/shoppingcart/list', function(data) {
// 					jsonData = JSON.stringify(data);
					
// 					$.ajax({ 
// 					    url: "http://localhost:8080/Checkout/shoppingcart/items", 
// 					    type: 'POST', 
// 					    dataType: 'json', 
// 						data: jsonData,
// 						contentType: "application/json; charset=utf-8",
// 						crossDomain: true,
// 					    success: function(dataObj) { 
// 					        alert(dataObj.id + " " + dataObj.name);
// 					    },
// 					    error:function(dataObj, status,er) { 
// 					        alert("error: " + dataObj + " status: " + status + " er:" + er);
// 					    }
// 					});
					
// 				});
 			}
		</script>
    </head>
    <body>
		<h1>Checkout teste</h1>
        	<button onclick="checkout()">Fechar Compra</button>
    </body>
</html>