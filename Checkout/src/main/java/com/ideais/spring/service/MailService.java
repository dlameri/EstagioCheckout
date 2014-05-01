package com.ideais.spring.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailService {

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendMail() throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo("leoSLRamos@gmail.com");
        msg.setText("teste");

        this.mailSender.send(msg);
    }
    
    public void sendPasswordMail(String password, String email) throws MailException {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo("leoSLRamos@gmail.com");
        msg.setText(password);

        this.mailSender.send(msg);
    }

}