package com.bezkoder.springjwt.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Reserva;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.ReservaRepository;

@Service
public class ReservaImpl {
	
	@Autowired
	private ReservaRepository reservaRepository;

	
	public ResponseEntity<?>insertar(Reserva reserva){
		reservaRepository.save(reserva);
        return ResponseEntity.ok(new MessageResponse("Reserva registrado satisfactoriamente!"));
		
	}
	
	public Reserva actualizar(Reserva reser) {
		Optional<Reserva> optionalReserva = reservaRepository.findById(reser.getId_reserva());
	if(optionalReserva.isEmpty()) {
		return null;
	}
	Reserva reservaEditado = optionalReserva.get();
		copiarCamposNoNulos(reser,reservaEditado);
		return reservaRepository.save(reservaEditado);
	}
	public List<Reserva> listarTodos(){
        return reservaRepository.findByEstado();

	}
	public Reserva listarById(Long id) {
		return reservaRepository.findById(id).get();
	}
	
	 public List<Reserva> eliminar(Long id) {
		 reservaRepository.deleteById(id);
	        return reservaRepository.findByEstado();
	    }
	
    public ResponseEntity<?> registrar(@Valid Reserva reserva){
    	 if(camposUnicosYnoNulos(reserva).getStatusCode().is4xxClientError()){
             return camposUnicosYnoNulos(reserva);
    	 }
    	 reservaRepository.save(reserva);
             return ResponseEntity.ok(new MessageResponse("reserva registrado satisfactoriamente!"));

         
    }
    private void copiarCamposNoNulos(Reserva fuente, Reserva destino) {
        if (fuente.getEstado() != null) {
            destino.setEstado(fuente.getEstado());
        }
        if (fuente.getFecha() != null) {
            destino.setFecha(fuente.getFecha());
        }
        if (fuente.getTipo() != null) {
            destino.setTipo(fuente.getTipo());
        }
        if (fuente.getHora() != null) {
            destino.setHora(fuente.getHora());
        }
       
    }
    	private ResponseEntity<?> camposUnicosYnoNulos(Reserva res)
    	{
    		if(res.getEstado() == null || res.getFecha() == null ||
    				res.getHora( ) == null || res.getInmueble() == null  || 
    						res.getTipo() == null) {
    			return ResponseEntity.badRequest().body(new MessageResponse("Error: Campos vacios"));
    		}
            return ResponseEntity.ok(new MessageResponse("Reserca registrado satisfactoriamente!"));

    	}
}
