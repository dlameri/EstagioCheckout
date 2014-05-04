package com.ideais.spring.controller.checkout.message;

public class ErrorMessageFactory {

	public static String buildErrorMessage(String status) {
		if ("errorQuantity".equals(status)) {
			return "Quantidade indisponível no estoque!";
		}
		if ("errorNumber".equals(status)) {
			return "Dado de input inválido.";
		}
		if ("errorFreight".equals(status)) {
			return "Erro ao calcular o frete, tente novamente.";
		}		
		if ("errorCep".equals(status)) {
			return "Cep inválido!";
		}		
		if ("errorWeight".equals(status)) {
			return "Peso do pedido excedido!";
		}		
		if ("errorVolume".equals(status)) {
			return "Volume do pedido excedido!";
		}		
		if ("errorDimension".equals(status)) {
			return "Dimensão do pedido excedido!";
		}		
		if ("noItems".equals(status)) {
			return "O carrinho está vazio, insira um item para continuar com a compra.";
		}
		return "Erro, tente novamente";
	}

}