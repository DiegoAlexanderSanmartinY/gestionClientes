package com.mail.gestionclientes.controllers;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mail.gestionclientes.dto.DtoSearhClient;
import com.mail.gestionclientes.models.Cliente;
import com.mail.gestionclientes.services.ClientServiceImp;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    ClientServiceImp clienteServiceImp;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable("id") Integer id) {
        try {
            Cliente cliente = clienteServiceImp.getById(id);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        try {
            List<Cliente> clientes = clienteServiceImp.findAll();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        try {
            clienteServiceImp.delete(id);
            return new ResponseEntity<>(id+" Destroy Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> findByBodyCriteria(@RequestBody DtoSearhClient dtoSearhClient) {
        try {
            List<Cliente> clientesSearch = clienteServiceImp.findByBodyCriteria(dtoSearhClient);
            return new ResponseEntity<>(clientesSearch, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {
        try {
            Cliente clienteCreated = clienteServiceImp.create(cliente);         
            return new ResponseEntity<>("Create Result: "+clienteCreated+"", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
        try {
            Cliente clienteUpdate = clienteServiceImp.update(id, cliente);
            return new ResponseEntity<>("Update Result: "+clienteUpdate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    

  




   

   
}
