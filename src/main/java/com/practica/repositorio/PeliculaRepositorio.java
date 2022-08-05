package com.practica.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practica.entidades.Pelicula;

@Repository
public interface PeliculaRepositorio extends JpaRepository<Pelicula,Integer> {
	
	
	 @Query("SELECT P FROM Pelicula P where P.titulo like %:name%")
	 List<Pelicula> buscarPeliculaPorNombre(@Param("name") String name);
	 
	 @Query("SELECT P FROM Genero P where P.genero_id = :genre")
	 List<Pelicula> buscarPeliculaPorGenero(@Param("genre") Integer genre);
}
