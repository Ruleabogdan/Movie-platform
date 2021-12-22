package com.streamingplatform.moviestreamingplatform.controller;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
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

    private MovieService movieService;

    @PostMapping("/")
    public Movie addMovie(@RequestBody Movie theMovie){

        // if they pass an id in JSON
        theMovie.setId(0);

        movieService.save(theMovie);

        return theMovie;
    }

    @GetMapping("/")
    public List<Movie> getMovies(){
        List<Movie> theMovies = movieService.findAll();

        return theMovies;
    }

    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable long movieId){

        Movie theMovie = movieService.getById(movieId);

        return theMovie;
    }

    @DeleteMapping("/{movieId}")
    public Movie deleteMovieById(@PathVariable long movieId){
        Movie theMovie =  movieService.getById(movieId);

        movieService.deleteById(movieId);

        return theMovie;
    }
}
