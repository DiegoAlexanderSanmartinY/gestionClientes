package com.mail.gestionclientes.models;

import java.util.List;

import lombok.Data;


@Data
public class Email {
   private String asunto;
   private List<String> to;
   private String body;
}
