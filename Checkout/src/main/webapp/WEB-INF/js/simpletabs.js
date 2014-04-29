$(document).ready(function(){
	
	$("#creditSelect").click(function(){
		$("#formBillet").hide(0);	
	    $("#formCard").show(500);
	});
	
	$("#billetSelect").click(function(){
		$("#formCard").hide(0);	
	    $("#formBillet").show(500);
	});
});
