package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.repository.MovieRepository;
import com.streamingplatform.moviestreamingplatform.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class MovieService implements IMovieService {

    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private UserService userService;
    @Override
    public Movie save(Movie theMovie) {
        theMovie.setId(null);
        User user = userService.getCurrentUser();
        user.addMovie(theMovie);
        movieRepository.save(theMovie);
        log.info("Saving movie to database {}", theMovie.getTitle());
        return theMovie;
    }

    @Override
    public Movie getById(long theId) {
        Optional<Movie> result = movieRepository.findById(theId);
        Movie theMovie = null;
        if (result.isPresent()) {
            theMovie = result.get();
        } else {
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
