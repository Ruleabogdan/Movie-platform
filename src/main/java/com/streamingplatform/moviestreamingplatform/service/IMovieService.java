package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;

import java.util.List;

public interface IMovieService {

    MovieDto save(Movie theMovie);

    MovieDto findById(long theId);

    MovieDto deleteById(long theId);

    List<MovieDto> findAll();
}
