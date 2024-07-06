package com.mail.gestionclientes.services;

import com.mail.gestionclientes.models.Email;

public interface IEmailService {

    public void sendEmail(Email email);
    
    public void enviarEmailWelcome(Email email, String nombre);
    
}
