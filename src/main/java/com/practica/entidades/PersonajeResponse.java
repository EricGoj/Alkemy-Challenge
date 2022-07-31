package com.practica.entidades;

public class PersonajeResponse {

	private String nombre;
	
	private byte[] imagen;

	public PersonajeResponse(String nombre, byte[] imagen) {
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
