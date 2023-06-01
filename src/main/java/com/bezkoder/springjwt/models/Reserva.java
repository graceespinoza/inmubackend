package com.bezkoder.springjwt.models;

import java.util.Date;

import javax.persistence.*;




@Entity
@Table(name="reserva")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_reserva;
	

	@Column(name="tipo", length=50)
	private String tipo;
	
	@Column(name="estado", length=50)
	private String estado;
	
	@Column(name="hora", length=50)
	private String hora;
	
	@Column(name="fecha", length=50)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="inmueble")
	private Inmueble inmueble;

	
	
	
	public Reserva() {
		super();
	}




	public Reserva(Long id_reserva, String tipo, String estado, String hora, Date fecha, Inmueble inmueble) {
		super();
		this.id_reserva = id_reserva;
		this.tipo = tipo;
		this.estado = estado;
		this.hora = hora;
		this.fecha = fecha;
		this.inmueble = inmueble;
	}




	public Long getId_reserva() {
		return id_reserva;
	}




	public void setId_reserva(Long id_reserva) {
		this.id_reserva = id_reserva;
	}




	public String getTipo() {
		return tipo;
	}




	public void setTipo(String tipo) {
		this.tipo = tipo;
	}




	public String getEstado() {
		return estado;
	}




	public void setEstado(String estado) {
		this.estado = estado;
	}




	public String getHora() {
		return hora;
	}




	public void setHora(String hora) {
		this.hora = hora;
	}




	public Date getFecha() {
		return fecha;
	}




	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}




	public Inmueble getInmueble() {
		return inmueble;
	}




	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}
	

}
