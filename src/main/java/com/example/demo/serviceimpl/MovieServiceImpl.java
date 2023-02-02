package com.example.demo.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	MovieRepository mRepo;
	
	// GET
	@Override
	public List<Movie> getAll() {
		return mRepo.findAll();
	}
	
	@Override
	public Optional<Movie> getById(Integer id) {
		return mRepo.findById(id);
	}
	
	// CREATE
	@Override
	public Movie insert(Movie movie) {
		return mRepo.save(movie);
	}
	
	// PUT
	@Override
	public Movie update(Integer id, Movie movie) {
		Optional<Movie> movieToUpdateOpt = mRepo.findById(id);
		if(movieToUpdateOpt.isPresent()) {
			Movie movieToUpdate = movieToUpdateOpt.get();
			movieToUpdate.setDirector(movie.getDirector());
			movieToUpdate.setTitle(movie.getTitle());
			movieToUpdate.setYear(movie.getYear());
			
			return mRepo.save(movieToUpdate);
		} else {
			return null;			
		}
	}
	
	// DELETE
	@Override
	public void delete(Integer id) {
		mRepo.deleteById(id);
	}
}
