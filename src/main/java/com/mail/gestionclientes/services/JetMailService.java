package com.mail.gestionclientes.services;

import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mail.gestionclientes.models.Email;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service("JetMailService")
public class JetMailService implements IEmailService {

 
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void sendEmail(Email email)  {

        /*TODO: To prove endpoint api/mail is working shoul be comment line 32 and 38*/

/* 32 */if (!(email.getAsunto().contains("cuenta"))) {
            List<String> to = new ArrayList<>();
            to.add("dasanmartin@yahoo.com");
            to.add("manuel@yahoo.com");
            email.setTo(to);
            email.setAsunto("Gracias por registrarte");
            // email.setBody("hola camaron con cola..");
/* 39 */}
       
    MailjetRequest request;
    MailjetResponse response;

    ClientOptions options = ClientOptions.builder()

    //TODO: cambiar las credenciales por valores almacenados en aplication.properties
        .apiKey(/* System.getenv("MJ_APIKEY_PUBLIC") */"39a283c21c4b67730d2e10ed61ab1fbb")
        .apiSecretKey(/* System.getenv("MJ_APIKEY_PRIVATE") */"4d04514ddeae453c921402b6e793a3cb")
        .build();
    
    MailjetClient client = new MailjetClient(options);

    request = new MailjetRequest(Emailv31.resource)
        .property(Emailv31.MESSAGES, new JSONArray()
            .put(new JSONObject()
                .put(Emailv31.Message.FROM, new JSONObject()
                    .put("Email", "dasanmartin.g@gmail.com")
                    .put("Name", "Me"))
                .put(Emailv31.Message.TO, new JSONArray()
                    .put(new JSONObject()
                        .put("Email", email.getTo().get(0))
                        .put("Name", "You")))
                .put(Emailv31.Message.SUBJECT, email.getAsunto())
                .put(Emailv31.Message.TEXTPART, email.getBody())
                .put(Emailv31.Message.HTMLPART, email.getBody())));
    try {
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    } catch (MailjetException e) {
        e.printStackTrace();
    }
    
    }

    @Override
    public void enviarEmailWelcome(Email email, String nombre) {
        Context context = new Context();
        context.setVariable("nombre", nombre);
        String emailMessageHtml = templateEngine.process("email", context);
        email.setBody(emailMessageHtml);
        sendEmail(email);
    }
}
