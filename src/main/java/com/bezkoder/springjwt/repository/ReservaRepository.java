package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;

import com.bezkoder.springjwt.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	@Modifying
	@Query("UPDATE Reserva u SET u.estado = null WHERE u.id_reserva = ?1")
	public void deleteById(Long id_reserva);

	  @Query("SELECT u FROM Reserva u WHERE u.estado != null ORDER BY u.id ASC")
	  public List<Reserva> findByEstado();
}
