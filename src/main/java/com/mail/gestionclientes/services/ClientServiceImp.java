package com.mail.gestionclientes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mail.gestionclientes.dto.DtoSearhClient;
import com.mail.gestionclientes.models.Cliente;
import com.mail.gestionclientes.models.Email;
import com.mail.gestionclientes.repository.ClientRepository;


@Service
public class ClientServiceImp implements IClientService {
    
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    EmailServiceImp emailServiceImp;

    @Override
    public Cliente getById(Integer id) {
    
        return clientRepository.findById(id).get();
    }
    @Override
    public void delete(Integer id) {
       clientRepository.deleteById(id);
    }
    @Override
    public Cliente create(Cliente client) {

        Email clientEmail = new Email();
        List<String> to = new ArrayList<>();
        to.add(client.getEmail());
        clientEmail.setTo(to);
        clientEmail.setAsunto("Su cuenta se creo correctamente");
        // clientEmail.setBody("Bienvenido a nuestro servicio");
        emailServiceImp.enviarEmailWelcome(clientEmail, client.getNombre());


        return clientRepository.save(client);
        
    }

    @Override
    public Cliente update(Integer id, Cliente client) {
       Cliente oldClient = clientRepository.findById(id).get();
       if(oldClient == null) return null;
       client.setId(id);
        return clientRepository.save(client);
    }

    @Override
    public List<Cliente> findAll() {
        return clientRepository.findAll();
    }
    @Override
    public List<Cliente> findByBodyCriteria(DtoSearhClient dtoSearhClient) {

        //String apellidos = dtoSearhClient.getApellidos();
        // String nombre = dtoSearhClient.getNombre();
        // String email = dtoSearhClient.getEmail();
        // String telefono = dtoSearhClient.getTelefono();
        //System.out.println(apellidos);
        return clientRepository.findByBodyCriteria(dtoSearhClient.getApellidos(), dtoSearhClient.getNombre(), dtoSearhClient.getEmail(), dtoSearhClient.getTelefono());
    }
   
    

    
}
