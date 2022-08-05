package com.practica.entidades.response;

import java.util.List;

public class PersonajeResponse {

	private String nombre;
	
	private byte[] imagen;
	
	private Integer edad;
	
	private Integer peso;
	
	private String historia;
	
	private List<String> peliculas;

	public PersonajeResponse(String nombre, byte[] imagen) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
	}

	
	public PersonajeResponse(String nombre, byte[] imagen, Integer edad, Integer peso, String historia,
			List<String> peliculas) {
		super();
		this.nombre = nombre;
		this.imagen = imagen;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		this.peliculas = peliculas;
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


	public Integer getEdad() {
		return edad;
	}


	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	public Integer getPeso() {
		return peso;
	}


	public void setPeso(Integer peso) {
		this.peso = peso;
	}


	public String getHistoria() {
		return historia;
	}


	public void setHistoria(String historia) {
		this.historia = historia;
	}


	public List<String> getPeliculas() {
		return peliculas;
	}


	public void setPeliculas(List<String> peliculas) {
		this.peliculas = peliculas;
	}





	

}
