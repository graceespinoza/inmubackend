package com.bezkoder.springjwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bezkoder.springjwt.models.Inmueble;
import com.bezkoder.springjwt.models.User;

public interface InmuebleRespository extends JpaRepository<Inmueble, Long>{

	  
	  @Modifying
	  @Query("UPDATE Inmueble u SET u.estado = null WHERE u.id_inmueble = ?1")
	  public void deleteById(Long id_inmueble);
	  
	  @Query("SELECT u FROM Inmueble u WHERE u.estado != null ORDER BY u.id ASC")
	  public List<Inmueble> findByEstado();

}
