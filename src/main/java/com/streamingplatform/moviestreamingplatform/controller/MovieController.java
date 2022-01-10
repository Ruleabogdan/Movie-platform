package com.streamingplatform.moviestreamingplatform.controller;

import com.streamingplatform.moviestreamingplatform.exceptions.ResourceNotFoundException;
import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.service.IMovieService;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private IMovieService movieService;

    @PostMapping
    public Movie addMovie(@RequestBody Movie theMovie) {

        theMovie.setId(null);

        movieService.save(theMovie);
        return theMovie;
    }

    @GetMapping
    public List<MovieDto> getMovies() {
        return movieService.findAll();
    }

    @GetMapping("/{movieId}")
    public MovieDto getMovieById(@PathVariable long movieId) {
        try {
            return movieService.findById(movieId);
        } catch (Exception e){
            throw new ResourceNotFoundException("There is no movie with id: " + movieId);
        }
    }

    @DeleteMapping("/{movieId}")
    public MovieDto deleteMovieById(@PathVariable long movieId) {
        try {
            return movieService.deleteById(movieId);
        }catch (Exception e){
            throw new ResourceNotFoundException("There is no movie with id: " + movieId);
        }
    }
}
