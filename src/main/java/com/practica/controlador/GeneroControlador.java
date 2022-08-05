package com.practica.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.practica.entidades.Genero;
import com.practica.repositorio.GeneroRepositorio;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class GeneroControlador {

	@Autowired
	private GeneroRepositorio repo;

	@PostMapping(path = "/gender", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createGenero(@RequestBody Genero genero) {
		try {
			repo.save(genero);
			return ResponseEntity.status(HttpStatus.CREATED).body("Genero creado exitosamente");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/gender")
	public ResponseEntity<?> listaGeneros() {
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}

}
