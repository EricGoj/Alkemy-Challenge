package com.practica.entidades.response;

import java.util.List;

public class PeliculaResponse {
	
	private String titulo;
	private String fechaDeCreacion;
	private String calificacion;
	private List<String> personajes;
	private byte[] imagen;
	

	public PeliculaResponse(String titulo, String fechaDeCreacion, String calificacion, List<String> personajes,
			byte[] imagen) {
		super();
		this.titulo = titulo;
		this.fechaDeCreacion = fechaDeCreacion;
		this.calificacion = calificacion;
		this.personajes = personajes;
		this.imagen = imagen;
	}
	
	
	public PeliculaResponse() {
		super();
	}


	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(String fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	public List<String> getPersonajes() {
		return personajes;
	}

	public void setPersonajes(List<String> personajes) {
		this.personajes = personajes;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	

}
