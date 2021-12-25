package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.repository.WatchlistRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WatchlistService implements IWatchilstService{

    private WatchlistRepository watchlistRepository;

    @Override
    public Movie addMovieToWatchlist(long movieId) {
        return null;
    }

    @Override
    public List<Movie> showWatchlist() {
        return null;
    }

    @Override
    public Movie removeMovieFromWatchlist(long movieId) {
        return null;
    }
}
