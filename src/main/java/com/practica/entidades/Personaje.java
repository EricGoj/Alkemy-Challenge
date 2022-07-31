package com.practica.entidades;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = "personajes")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="personaje_id")
public class Personaje  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "personaje_id")
	private Integer id;

	private String nombre;

	private Integer edad;

	private Integer peso;

	private String historia;

	@ManyToMany(mappedBy = "personajes",cascade=CascadeType.ALL)
	private Set<Pelicula> Peliculas;
	
	@Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    private byte[] imagen;
	
	public Personaje() {
		super();
	}

	public Personaje(String nombre, Integer edad, Integer peso, String historia, Set<Pelicula> peliculas,
			byte[] imagen) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.historia = historia;
		Peliculas = peliculas;
		this.imagen = imagen;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
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


	public Set<Pelicula> getPeliculas() {
		return Peliculas;
	}


	public void setPeliculas(Set<Pelicula> peliculas) {
		Peliculas = peliculas;
	}


	public byte[] getImagen() {
		return imagen;
	}


	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	

}
