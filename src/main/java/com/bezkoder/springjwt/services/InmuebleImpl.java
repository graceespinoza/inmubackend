package com.bezkoder.springjwt.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Inmueble;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.InmuebleRespository;

@Service
public class InmuebleImpl {
	
	@Autowired
	private InmuebleRespository inmueblerepository;
	
	public ResponseEntity<?>insertar(Inmueble inmu){
		inmueblerepository.save(inmu);
        return ResponseEntity.ok(new MessageResponse("Usuario registrado satisfactoriamente!"));
		
	}
	
	public Inmueble actualizar(Inmueble inmue) {
		Optional<Inmueble> optionalInmue = inmueblerepository.findById(inmue.getId_inmueble());
	if(optionalInmue.isEmpty()) {
		return null;
	}
		Inmueble inmueEditado = optionalInmue.get();
		copiarCamposNoNulos(inmue,inmueEditado);
		return inmueblerepository.save(inmueEditado);
	}
	public List<Inmueble> listarTodos(){
        return inmueblerepository.findByEstado();

	}
	public Inmueble listarById(Long id) {
		return inmueblerepository.findById(id).get();
	}
	
	 public List<Inmueble> eliminar(Long id) {
		 inmueblerepository.deleteById(id);
	        return inmueblerepository.findByEstado();
	    }
	
    public ResponseEntity<?> registrar(@Valid Inmueble inmueble){
    	 if(camposUnicosYnoNulos(inmueble).getStatusCode().is4xxClientError()){
             return camposUnicosYnoNulos(inmueble);
    	 }
             inmueblerepository.save(inmueble);
             return ResponseEntity.ok(new MessageResponse("Usuario registrado satisfactoriamente!"));

         
    }
    private void copiarCamposNoNulos(Inmueble fuente, Inmueble destino) {
        if (fuente.getNombre() != null) {
            destino.setNombre(fuente.getNombre());
        }
        if (fuente.getDireccion() != null) {
            destino.setDireccion(fuente.getDireccion());
        }
        if (fuente.getPrecio() != 0) {
            destino.setPrecio(fuente.getPrecio());
        }
        if (fuente.getEstado() != null) {
            destino.setEstado(fuente.getEstado());
        }
        if (fuente.getTipo_inmuble()!= null) {
        destino.setTipo_inmuble(fuente.getTipo_inmuble());
        }
       
    }
    	private ResponseEntity<?> camposUnicosYnoNulos(Inmueble inmuele)
    	{
    		if(inmuele.getNombre() == null || inmuele.getDireccion() == null ||
    			inmuele.getEstado( ) == null || inmuele.getPrecio() == 0 || 
    			inmuele.getTipo_inmuble() == null) {
    			return ResponseEntity.badRequest().body(new MessageResponse("Error: Campos vacios"));
    		}
            return ResponseEntity.ok(new MessageResponse("Usuario registrado satisfactoriamente!"));

    	}
}