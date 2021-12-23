package com.streamingplatform.moviestreamingplatform.controller;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.service.MovieService;
import com.streamingplatform.moviestreamingplatform.service.UserService;
import com.streamingplatform.moviestreamingplatform.service.WatchlistService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class WatchlistController {

    private UserService userService;
    private MovieService movieService;
    private WatchlistService watchlistService;

    @PostMapping("/{userId}/watchlist/{movieId}")
    public Movie addMovieToWatchlist(@PathVariable long userId, @PathVariable long movieId){
        return watchlistService.addMovieToWatchlist(movieId);
    }

    @GetMapping("{userId}/watchlist")
    public List<Movie> showWatchlist(@PathVariable long userId){
        return watchlistService.showWatchlist();
    }

    @DeleteMapping("{userId}/watchlist/{movieId}")
    public Movie deleteMovieByIdFromWatchlist(@PathVariable long userId, @PathVariable long movieId){
        return watchlistService.removeMovieFromWatchlist(movieId);
    }
}
