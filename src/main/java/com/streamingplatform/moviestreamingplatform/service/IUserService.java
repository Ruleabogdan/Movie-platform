package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;

import java.util.Collection;
import java.util.List;

public interface IUserService {

    public User saveUser(User theUser);

    public List<User> findAll();

    public User deleteById(long userId);

    public User findById(long userId);

    public Role saveRole(Role role);

    public User addRoleToUser(String userName, String roleName);

    public Movie addMovieToWatchlist(long movieId);

    public Collection<Movie> showWatchlist();

    public Movie deleteMovieFromWatchlist(long movieId);

    public User getCurrentUser();
}
