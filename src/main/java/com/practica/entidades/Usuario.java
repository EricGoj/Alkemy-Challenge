package com.practica.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private Integer idUsuario;
	private String usuario;
	private String email;
	private String password;
	private String token;
	

	public Usuario() {
		super();
	}
	public Usuario(Integer idUsuario, String usuario, String email, String password, String token) {
		super();
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.email = email;
		this.password = password;
		this.token = token;
	}
	
	
	public Usuario(String usuario, String email, String password) {
		super();
		this.usuario = usuario;
		this.email = email;
		this.password = password;
	}
	
	
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	

}
