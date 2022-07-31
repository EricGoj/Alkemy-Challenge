package com.practica.repositorio;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practica.entidades.Personaje;

@Repository
public interface PersonajeRepositorio extends JpaRepository<Personaje,Integer> {

	@Query("SELECT P FROM Personaje P where P.nombre like %:name%")
	List<Personaje> buscarPersonajePorNombre(@Param("name") String name);
	

    @Query("SELECT P FROM Personaje P where P.edad = :age")
    List<Personaje> buscarPersonajePorEdad(@Param("age") Integer age);
	
}
