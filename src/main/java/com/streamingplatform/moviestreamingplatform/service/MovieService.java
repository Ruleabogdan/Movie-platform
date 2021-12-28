package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;
<<<<<<< Updated upstream
=======
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.mapper.ImyMapper;
>>>>>>> Stashed changes
import com.streamingplatform.moviestreamingplatform.repository.MovieRepository;

<<<<<<< Updated upstream
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService {

    private MovieRepository movieRepository;
    private UserService userService;
<<<<<<< Updated upstream

    @Override
    public Movie save(Movie theMovie) {
        theMovie.setId(0);
        theMovie.setUser(userService.findById(1));
        movieRepository.save(theMovie);
        theMovie = movieRepository.getById(theMovie.getId());
        return theMovie;
=======
    private ImyMapper myMapper;

    @Override
    public MovieDto save(Movie theMovie) {
        theMovie.setId(null);
        User user = userService.getCurrentUser();
        user.addMovie(theMovie);
        movieRepository.save(theMovie);
        log.info("Saving movie to database {}", theMovie.getTitle());
        return myMapper.movieToMovieDto(theMovie);
>>>>>>> Stashed changes
    }

    @Override
    public MovieDto getById(long theId) {
        Optional<Movie> result = movieRepository.findById(theId);
        Movie theMovie = null;
        if (result.isPresent()) {
            theMovie = result.get();
        } else {
            throw new RuntimeException("Did not find the movie with the id - " + theId);
        }
        return myMapper.movieToMovieDto(theMovie);
    }

    @Override
    public MovieDto deleteById(long theId) {
        Movie theMovie = movieRepository.getById(theId);
        movieRepository.deleteById(theId);
        return myMapper.movieToMovieDto(theMovie);
    }

    @Override
    public List<MovieDto> findAll() {
        return myMapper.listAllMoviesDto(movieRepository.findAll());
    }
}
