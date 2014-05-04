package com.ideais.spring.service;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.ideais.spring.domain.checkout.PurchaseOrder;
import com.ideais.spring.domain.checkout.ShoppingCartLine;

public class MailService {
	private static final String GREETINGS = ", obrigado por comprar em nossa Loja.";
	private static final String ORDER_DETAILS = "\nA seguir algumas informações sobre a compra";
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
        msg.setTo(email);
        msg.setText(formatMessage(order));

        this.mailSender.send(msg);
    }
    
    public void sendPasswordMail(String password, String email) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(email);
        msg.setText(password);

        this.mailSender.send(msg);
    }
    public String formatMessage(PurchaseOrder order){
    	String message = "Olá Sr(a) "+order.getCustomer().getName()+ GREETINGS + ORDER_DETAILS;
    	
    	message+="\nTotal da Compra: "+order.getFormattedTotalAmount();
    	message+= insertDeliveryInfo(order);
    	
    	return message;
    }
	
	private String insertDeliveryInfo(PurchaseOrder order){
		String message = "\nOs itens serão enviados para o seguinte destinatário:\n";
		message+= order.getShippingAddress().getFormattedAdress();
		return message;
	}
}