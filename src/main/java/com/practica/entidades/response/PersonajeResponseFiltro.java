package com.practica.entidades.response;

public class PersonajeResponseFiltro {
	
	private String nombre;
	private byte[] imagen;
	
	
	public PersonajeResponseFiltro(String nombre, byte[] imagen) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public byte[] getImagen() {
		return imagen;
	}


	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
	
	

}
