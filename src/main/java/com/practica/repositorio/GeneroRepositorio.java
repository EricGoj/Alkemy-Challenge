package com.practica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entidades.Genero;

@Repository
public interface GeneroRepositorio extends JpaRepository<Genero,Integer> {
	
}
