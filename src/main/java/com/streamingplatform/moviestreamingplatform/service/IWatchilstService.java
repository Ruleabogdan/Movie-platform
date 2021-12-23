package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;

import java.util.List;

public interface IWatchilstService {
    public Movie addMovieToWatchlist(long movieId);

    public List<Movie> showWatchlist();

    public Movie removeMovieFromWatchlist(long movieId);
}
