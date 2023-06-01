package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.*;
import com.bezkoder.springjwt.services.ReservaImpl;

@RestController
@RequestMapping("/api/reserva")
@CrossOrigin(origins = "*")
public class ReservaController {
	
	@Autowired
	private ReservaImpl reserservice;
	
	
	@GetMapping
	@PreAuthorize("hasRole('MODERATOR')")
	public List<Reserva>listar(){
		return reserservice.listarTodos();
	}
	
	@GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') ")
	public Reserva getReservaById(@PathVariable("id_reserva")Long id_reserva) {
		return reserservice.listarById(id_reserva);
	}

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR') ")
    public ResponseEntity<?> insertar(@RequestBody Reserva reservaBody){
        return reserservice.insertar(reservaBody);
    }
    
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('MODERATOR') ")
    public Reserva actualizar(@PathVariable Long id_reserva, @RequestBody Reserva reservaBody){
        
    	reservaBody.setId_reserva(id_reserva);
        
        return reserservice.actualizar(reservaBody);
    }
    @PutMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('MODERATOR') ")
    public List<Reserva> eliminar(@PathVariable Long id_reserva){
        return reserservice.eliminar(id_reserva);
    } 

}
