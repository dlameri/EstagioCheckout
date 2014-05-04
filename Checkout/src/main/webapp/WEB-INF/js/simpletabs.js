$(document).ready(function() {
	
	$("#creditSelect").click(function() {
		$("#formBillet").hide(0);	
	    $("#formCard").show(500);
	});
	
	$("#billetSelect").click(function() {
		$("#formCard").hide(0);	
	    $("#formBillet").show(500);
	});
	
	$("#orders").click(function() {
		if($("#orderList").is(':hidden')) {
		    $("#orderList").show(500);
		    $("#orders").html("Ocultar compras");
		} else {
		    $("#orderList").hide(300);
		    $("#orders").html("Mostrar compras");
		}
	});
	
	$("#orderItems").click(function() {
		if($("#orderItemsDetails").is(':hidden')) {
		    $("#orderItemsDetails").show(500);
		    $("#orderItems").html("Ocultar itens do pedido");
		} else {
		    $("#orderItemsDetails").hide(300);
		    $("#orderItems").html("Mostrar itens do pedido");
		}
	});
});