package com.mail.gestionclientes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mail.gestionclientes.models.Email;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImp implements IEmailService{

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(Email email) {
        if (!(email.getAsunto().contains("cuenta"))) {
            List<String> to = new ArrayList<>();
            to.add("marisol@gmail.com");
            to.add("manuel@yahoo.com");
            email.setTo(to);
            email.setAsunto("Gracias por registrarte");
            // email.setBody("hola camaron con cola..");
        } 
            
        String usuario = "dasanmartin@gmail.com";
        String password = "123456";

        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");

        Session sesion =  Session.getInstance(props, new Authenticator() {    
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        });

        try {
            Message message = new MimeMessage(sesion);
            message.setFrom(new InternetAddress(usuario));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo().get(0)));
            message.setSubject(email.getAsunto());
            message.setText(email.getBody());

            Transport.send(message);
            
        } catch (Exception e) {
            System.out.println(e);
        }

        
    }

    public void enviarEmailWelcome(Email email, String nombre) {
        Context context = new Context();
        context.setVariable("nombre", nombre);
        String emailMessageHtml = templateEngine.process("email", context);
        email.setBody(emailMessageHtml);
        sendEmail(email);

    }

}
