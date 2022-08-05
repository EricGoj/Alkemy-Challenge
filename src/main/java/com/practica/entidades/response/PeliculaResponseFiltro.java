package com.practica.entidades.response;

public class PeliculaResponseFiltro {
	
	private String nombre;

	private String fechaDeCreacion;
	
	 private byte[] imagen;

	public PeliculaResponseFiltro(String nombre, String fechaDeCreacion, byte[] imagen) {
		super();
		this.nombre = nombre;
		this.fechaDeCreacion = fechaDeCreacion;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaDeCreacion() {
		return fechaDeCreacion;
	}

	public void setFechaDeCreacion(String fechaDeCreacion) {
		this.fechaDeCreacion = fechaDeCreacion;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	
	
	

}
