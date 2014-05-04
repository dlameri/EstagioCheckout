package com.ideais.spring.domain.checkout;

public enum StatusOfOrder {
	PAID("Pago"), WAITING_FOR_PAYMENT("Aguardando pagamento"), SENT("Enviado"), FINISHED("Finalizada"), DECLINED("Cancelada");
	
	private final String status;

	StatusOfOrder(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
}