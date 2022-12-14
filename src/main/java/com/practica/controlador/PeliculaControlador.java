package com.practica.controlador;

import java.util.ArrayList;
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

import com.practica.entidades.Genero;
import com.practica.entidades.Pelicula;
import com.practica.entidades.response.PeliculaResponse;
import com.practica.entidades.response.PeliculaResponseFiltro;
import com.practica.excepciones.IdNotFoundException;
import com.practica.repositorio.PeliculaRepositorio;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class PeliculaControlador {

	@Autowired
	private PeliculaRepositorio servicioPelicula;
	

	// Create
	@PostMapping(path = "/movies", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createPelicula(@RequestBody Pelicula pelicula) {
		try {
			servicioPelicula.save(pelicula);
			return ResponseEntity.status(HttpStatus.CREATED).body("Pelicula agregada con exito");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Read
	@GetMapping(path = "/movies", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> listaPeliculas(@RequestParam(required = false, name = "name") String name,
			@RequestParam(required = false, name = "genre") Integer idGenero) {
		List<Pelicula> peliculas = servicioPelicula.findAll();
		List<Object> listaFront = new ArrayList<>();
		if (name == null && idGenero == null) {
			for (Pelicula pelis : peliculas) {
				PeliculaResponse front = new PeliculaResponse(pelis.getTitulo(), pelis.getFechaDeCreacion(),
						pelis.getCalificacion(), pelis.getNombrePersonaje(), pelis.getImagen());
				listaFront.add(front);
			}
		} else if (!(idGenero == null)) {
			for (Pelicula pelis : peliculas) {
				for (Genero generos : pelis.getGeneros()) {
					if (generos.getId() == idGenero) {
						PeliculaResponseFiltro front = new PeliculaResponseFiltro(pelis.getTitulo(),
								pelis.getFechaDeCreacion(), pelis.getImagen());
						listaFront.add(front);
					}
				}
			}
		} else if (!(name == null)) {
			peliculas = servicioPelicula.buscarPeliculaPorNombre(name);
			for (Pelicula pelis : peliculas) {
				PeliculaResponseFiltro front = new PeliculaResponseFiltro(pelis.getTitulo(), pelis.getFechaDeCreacion(),
						pelis.getImagen());
				listaFront.add(front);
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(listaFront);
	}

	// Update
	@PutMapping(path = "/movies/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
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
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Cambios realizados con exito");
		} else {
			 throw new IdNotFoundException(id);
		}
	}

	@DeleteMapping(path = "/movies/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminarPelicula(@PathVariable Integer id) {
		Optional<Pelicula> persoData = servicioPelicula.findById(id);
		if (persoData.isPresent()) {
			servicioPelicula.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Elimnado con exito");
		} else {
			throw new IdNotFoundException(id);
		}
	}
}
