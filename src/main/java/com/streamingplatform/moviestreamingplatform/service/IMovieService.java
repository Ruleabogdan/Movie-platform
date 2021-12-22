package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;

import java.util.List;

public interface IMovieService {
    public Movie save(Movie theMovie);

    public Movie getById(long theId);

    public Movie deleteById(long theId);

    public List<Movie> findAll();
}
