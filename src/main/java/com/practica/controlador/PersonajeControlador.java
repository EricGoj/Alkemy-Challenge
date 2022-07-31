package com.practica.controlador;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practica.entidades.Pelicula;
import com.practica.entidades.Personaje;
import com.practica.entidades.PersonajeResponse;
import com.practica.repositorio.PersonajeRepositorio;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class PersonajeControlador {

	@Autowired
	private PersonajeRepositorio servicioPersonaje;

	// CRUD
	// Create
	@PostMapping(path = "/characters", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPersonaje(@RequestBody Personaje personaje) {
		try {
			servicioPersonaje.save(personaje);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Read
	@GetMapping(path = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listaPersonajes(@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "age") Integer age,
			@RequestParam(required = false, name = "idMovie") Integer idMovie) {
		List<Personaje> personajes = servicioPersonaje.findAll();
		if (!(idMovie == null)) {
			for (Personaje perso : personajes) {
				for (Pelicula peliculas : perso.getPeliculas()) {
					if (peliculas.getId() == idMovie) {
						PersonajeResponse front = new PersonajeResponse(perso.getNombre(), perso.getImagen());
						return new ResponseEntity<>(front, HttpStatus.OK);
					}
				}
			}
		}
		if (!(age == null)) {
			personajes = servicioPersonaje.buscarPersonajePorEdad(age);
			for (Personaje a : personajes) {
				PersonajeResponse per = new PersonajeResponse(a.getNombre(), a.getImagen());
				return new ResponseEntity<>(per, HttpStatus.OK);
			}
		} else if (!(name == null)) {
			personajes = servicioPersonaje.buscarPersonajePorNombre(name);
			for (Personaje a : personajes) {
				PersonajeResponse per = new PersonajeResponse(a.getNombre(), a.getImagen());
				return new ResponseEntity<>(per, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(servicioPersonaje.findAll(), HttpStatus.OK);
	}

	// Update
	@PutMapping(path = "/characters/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePersonaje(@RequestBody Personaje personaje, @PathVariable Integer id) {
		Optional<Personaje> persoData = servicioPersonaje.findById(id);
		if (persoData.isPresent()) {
			Personaje PersonajeA = persoData.get();
			PersonajeA.setNombre(personaje.getNombre());
			PersonajeA.setHistoria(personaje.getHistoria());
			PersonajeA.setPeso(personaje.getPeso());
			PersonajeA.setEdad(personaje.getEdad());
			PersonajeA.setImagen(personaje.getImagen());
			return new ResponseEntity<>(servicioPersonaje.save(PersonajeA), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	// Delete
	@DeleteMapping(path = "/characters/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminarPersonaje(@PathVariable Integer id) {
		Optional<Personaje> persoData = servicioPersonaje.findById(id);
		if (persoData.isPresent()) {
			servicioPersonaje.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
