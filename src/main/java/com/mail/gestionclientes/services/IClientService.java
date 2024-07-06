package com.mail.gestionclientes.services;


import java.util.List;

import com.mail.gestionclientes.dto.DtoSearhClient;
import com.mail.gestionclientes.models.Cliente;

public interface IClientService {

    public Cliente getById(Integer id);

    public void delete(Integer id);

    public Cliente create(Cliente client) ;

    public Cliente update(Integer id, Cliente client);

    public List<Cliente> findAll();

    public List<Cliente> findByBodyCriteria(DtoSearhClient dtoSearhClient);
}