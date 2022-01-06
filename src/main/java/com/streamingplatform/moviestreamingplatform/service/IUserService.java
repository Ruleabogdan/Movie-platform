package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserDto;

import java.util.List;

public interface IUserService {

    UserDto saveUser(User theUser);

    List<UserDto> findAll();

    UserDto deleteById(long userId);

    UserDto findById(long userId);

    Role saveRole(Role role);

    UserDto addRoleToUser(String userName,
                       String roleName);

    MovieDto addMovieToWatchlist(long userId,
                                 long movieId);

    List<MovieDto> showWatchlist(long userId);

    MovieDto deleteMovieFromWatchlist(long userId,
                                      long movieId);

    User getUser(String username);

    User getCurrentUser();
}
