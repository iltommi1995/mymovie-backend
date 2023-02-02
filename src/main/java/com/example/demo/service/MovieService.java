package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Movie;

public interface MovieService {
	List<Movie> getAll();

	Optional<Movie> getById(Integer id);

	Movie insert(Movie movie);

	Movie update(Integer id, Movie movie);

	void delete(Integer id);

}
