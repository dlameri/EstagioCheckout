$(function() {
	$('.cep').mask('00000-000');
	$('.phoneNumber').mask('(00) 00000-0000');
	$('.cpf').mask('000.000.000-00', {reverse: true});
	$(".registerForm").validationEngine();
	$(".creditcard").mask('0000-0000-0000-0000');
	$("#securityCode").mask('000');
	$(".formNew").validationEngine();
	$("#formCard").validationEngine();
	$(".form").validationEngine();
	
});
