package com.mail.gestionclientes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mail.gestionclientes.dto.DtoSearhClient;
import com.mail.gestionclientes.models.Cliente;

@Repository
public interface ClientRepository extends JpaRepository<Cliente, Integer> {


    @Query(
        "FROM Cliente c WHERE " + 
        "c.apellidos LIKE CONCAT('%',:apellidos,'%')" +
        "OR c.nombre LIKE CONCAT('%',:nombre,'%')" +
        "OR c.email LIKE CONCAT('%',:email,'%')" +
        "OR c.telefono LIKE CONCAT('%',:telefono,'%')"
        
      
    )
    // List<Cliente> findByBodyCriteria(DtoSearhClient dtoSearhClient);
    List<Cliente> findByBodyCriteria(String apellidos, String nombre, String email, String telefono);

}
