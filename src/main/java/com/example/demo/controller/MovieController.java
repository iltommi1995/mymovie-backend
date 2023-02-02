package com.example.demo.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Movie;
import com.example.demo.service.MovieService;

@RestController
@RequestMapping("/movies")
@CrossOrigin
public class MovieController {
	@Autowired
	MovieService mService;
	
	// GET
	@GetMapping
	public ResponseEntity<List<Movie>> getAll() {
		try {
			return	ResponseEntity.ok().body(mService.getAll());
		} catch (Exception e) {
			return	ResponseEntity.internalServerError().body(null);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Movie>> getById(@PathVariable("id") Integer id) {
		try {
			return	ResponseEntity.ok().body(mService.getById(id));
		} catch (Exception e) {
			return	ResponseEntity.internalServerError().body(null);
		}
	}
	
	// POST
	@PostMapping
	public ResponseEntity<Movie> create(@RequestBody Movie movie) {
		try {
			Movie createdMovie = mService.insert(movie);
			 URI location = ServletUriComponentsBuilder
				      .fromCurrentRequest().path("/{id}")
				      .buildAndExpand(movie.getId()).toUri();
			return	ResponseEntity.created(location).body(createdMovie);
		} catch (Exception e) {
			return	ResponseEntity.internalServerError().body(null);
		}
	}
	
	// PUT
	@PutMapping("/{id}")
	public ResponseEntity<Movie> update(@PathVariable("id") Integer id, @RequestBody Movie movie) {
		try {
			return	ResponseEntity.ok().body(mService.update(id, movie));
		} catch (Exception e) {
			return	ResponseEntity.internalServerError().body(null);
		}
	}
	
	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		try {
			mService.delete(id);
			return	ResponseEntity.noContent().build();
		} catch (Exception e) {
			return	ResponseEntity.notFound().build();
		}
	}
}
