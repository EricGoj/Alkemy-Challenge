package com.practica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practica.entidades.Usuario;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

}
