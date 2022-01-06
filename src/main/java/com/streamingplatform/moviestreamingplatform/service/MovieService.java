package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.mapper.ICustomMapper;
import com.streamingplatform.moviestreamingplatform.repository.MovieRepository;

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
    private UserService userService;
    private ICustomMapper customMapper;

    @Override
    public MovieDto save(Movie theMovie) {
        theMovie.setId(null);
        User user = userService.getCurrentUser();
        user.addMovie(theMovie);
        movieRepository.save(theMovie);
        log.info("Saving movie to database {}", theMovie.getTitle());
        return customMapper.movieToMovieDto(theMovie);
    }

    @Override
    public MovieDto findById(long theId) {
        Optional<Movie> result = movieRepository.findById(theId);
        Movie theMovie;
        if (result.isPresent()) {
            theMovie = result.get();
        } else {
            throw new RuntimeException("Did not find the movie with the id - " + theId);
        }
        return customMapper.movieToMovieDto(theMovie);
    }

    @Override
    public MovieDto deleteById(long theId) {
        Movie theMovie = movieRepository.getById(theId);
        movieRepository.deleteById(theId);
        return customMapper.movieToMovieDto(theMovie);
    }

    @Override
    public List<MovieDto> findAll() {
        return customMapper.listAllMoviesDto(movieRepository.findAll());
    }
}
