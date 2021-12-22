package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.repository.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {

    private MovieRepository movieRepository;

    @Override
    public Movie save(Movie theMovie)
    {
        theMovie.setId(0);

        movieRepository.save(theMovie);

        theMovie = movieRepository.getById(theMovie.getId());
        return theMovie;
    }

    @Override
    public Movie getById(long theId) {
        Optional<Movie> result = movieRepository.findById(theId);

        Movie theMovie = null;

        if(result.isPresent()){
            theMovie = result.get();
        }
        else {
            throw new RuntimeException("Did not find the movie with the id - " + theId);
        }

        return theMovie;
    }

    @Override
    public Movie deleteById(long theId) {
        Movie theMovie = movieRepository.getById(theId);
        movieRepository.deleteById(theId);
        return theMovie;
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
