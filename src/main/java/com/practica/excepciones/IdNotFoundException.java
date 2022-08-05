package com.practica.excepciones;

public class IdNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IdNotFoundException(Integer id) {
		super(String.format("El id %d no funciona", id));
	}

}
