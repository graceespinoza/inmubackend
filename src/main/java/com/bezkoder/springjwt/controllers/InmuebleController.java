package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.security.access.prepost.PreAuthorize;

import com.bezkoder.springjwt.models.Inmueble;
import com.bezkoder.springjwt.services.InmuebleImpl;

@RestController
@RequestMapping("/api/inmueble")
@CrossOrigin(origins = "*")
public class InmuebleController {
	
	@Autowired
	private InmuebleImpl inmuebleService;
	
	@GetMapping
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Inmueble>listar(){
		return inmuebleService.listarTodos();
	}
	
	@GetMapping("/{id}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public Inmueble getInmuebleById(@PathVariable("id_inmueble")Long id_inmueble) {
		return inmuebleService.listarById(id_inmueble);
	}

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insertar(@RequestBody Inmueble inmuebleBody){
        return inmuebleService.insertar(inmuebleBody);
    }
    
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Inmueble actualizar(@PathVariable Long id_inmueble, @RequestBody Inmueble inmuebleBody){
        
    	inmuebleBody.setId_inmueble(id_inmueble);
        
        return inmuebleService.actualizar(inmuebleBody);
    }
    @PutMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Inmueble> eliminar(@PathVariable Long id_inmueble){
        return inmuebleService.eliminar(id_inmueble);
    } 
}
