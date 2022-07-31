package com.practica.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.entidades.Usuario;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer> {

}
