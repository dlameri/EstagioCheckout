package com.ideais.spring.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import com.ideais.spring.domain.checkout.PurchaseOrder;

public class MailService {
	private static final String GREETINGS = ", obrigado por comprar em nossa Loja.";
	private static final String ORDER_DETAILS = "\n\nA seguir algumas informações sobre a compra";
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendMail(String email, PurchaseOrder order) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setSubject("Ideais Electronicts - compra realizada - Data: " + order.getFormattedPurchaseDate());
        msg.setTo(email);
        msg.setText(formatMessage(order));

        this.mailSender.send(msg);
    }
    
    public void sendPasswordMail(String password, String email) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setSubject("Ideais Electronicts - recuperação de senha.");
        msg.setTo(email);
        msg.setText("Olá, sua senha recuperada é: \n" + password);

        this.mailSender.send(msg);
    }
    public String formatMessage(PurchaseOrder order){
    	String message = "Olá Sr(a) " + order.getCustomer().getName() + GREETINGS + ORDER_DETAILS;
    	
    	message+="\nTotal da Compra: " + order.getFormattedTotalAmount();
    	message+= insertDeliveryInfo(order);
    	
    	return message;
    }
	
	private String insertDeliveryInfo(PurchaseOrder order){
		String message = "\n\nOs itens serão enviados para o seguinte destinatário:\n";
		message+= order.getShippingAddress().getFormattedAdress();
		return message;
	}
}