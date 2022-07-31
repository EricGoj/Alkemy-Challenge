package com.practica.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practica.entidades.Genero;
import com.practica.entidades.Pelicula;
import com.practica.entidades.PeliculaResponse;
import com.practica.repositorio.PeliculaRepositorio;

@RestController
public class PeliculaControlador {

	@Autowired
	private PeliculaRepositorio servicioPelicula;
	

	// Create
	@PostMapping(path = "/peliculas", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPelicula(@RequestBody Pelicula pelicula) {
		try {
			servicioPelicula.save(pelicula);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Read
	@GetMapping(path = "/peliculas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listaPeliculas(@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "idGenero") Integer idGenero) {
		List<Pelicula> peliculas = servicioPelicula.findAll();
		if (!(idGenero == null)) {
			for (Pelicula pelis : peliculas) {
				for (Genero generos : pelis.getGeneros()) {
					if (generos.getId() == idGenero) {
						PeliculaResponse front = new PeliculaResponse(pelis.getTitulo(), pelis.getFechaDeCreacion(),
								pelis.getImagen());
						return new ResponseEntity<>(front, HttpStatus.OK);
					}
				}
			}
		}
		else if (!(name == null)) {
			peliculas = servicioPelicula.buscarPeliculaPorNombre(name);
			for (Pelicula pelis : peliculas) {
				PeliculaResponse front = new PeliculaResponse(pelis.getTitulo(), pelis.getFechaDeCreacion(),
						pelis.getImagen());
				return new ResponseEntity<>(front, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(peliculas, HttpStatus.OK);
	}

	// Update
	@PutMapping(path = "/peliculas/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updatePelicula(@RequestBody Pelicula pelicula, @PathVariable Integer id) {
		Optional<Pelicula> peliData = servicioPelicula.findById(id);
		if (peliData.isPresent()) {
			Pelicula PeliculaA = peliData.get();
			PeliculaA.setTitulo(pelicula.getTitulo());
			PeliculaA.setCalificacion(pelicula.getFechaDeCreacion());
			PeliculaA.setCalificacion(pelicula.getCalificacion());
			PeliculaA.setPersonajes(pelicula.getPersonajes());
			PeliculaA.setGeneros(pelicula.getGeneros());
			PeliculaA.setImagen(pelicula.getImagen());
			return new ResponseEntity<>(servicioPelicula.save(PeliculaA), HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping(path = "/peliculas/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminarPelicula(@PathVariable Integer id) {
		Optional<Pelicula> persoData = servicioPelicula.findById(id);
		if (persoData.isPresent()) {
			servicioPelicula.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
