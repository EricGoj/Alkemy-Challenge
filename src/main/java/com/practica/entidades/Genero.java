package com.practica.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "generos")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="genero_id")
public class Genero {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="genero_id")
	private Integer id;
	
	private String nombre;
	
	@ManyToMany(mappedBy="generos")
	private Set<Pelicula> peliculas;

	
	public Genero() {
		super();
	}

	public Genero(Integer id, String nombre, Set<Pelicula> peliculas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.peliculas = peliculas;
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

	public Set<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(Set<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	

}
