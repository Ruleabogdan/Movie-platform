package com.streamingplatform.moviestreamingplatform.service;

<<<<<<< Updated upstream
import com.streamingplatform.moviestreamingplatform.model.Movie;
=======
import com.streamingplatform.moviestreamingplatform.model.Role;
>>>>>>> Stashed changes
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserDto;

import java.util.List;

public interface IUserService {

<<<<<<< Updated upstream
    public User save(User theUser);
=======
    UserDto saveUser(User theUser);
>>>>>>> Stashed changes

    List<UserDto> findAll();

    UserDto deleteById(long userId);

    UserDto findById(long userId);

<<<<<<< Updated upstream


=======
    Role saveRole(Role role);

    User addRoleToUser(String userName,
                       String roleName);

    MovieDto addMovieToWatchlist(long userId,
                                 long movieId);

    List<MovieDto> showWatchlist(long userId);

    MovieDto deleteMovieFromWatchlist(long userId,
                                      long movieId);

    User getUser(String username);

    User getCurrentUser();
>>>>>>> Stashed changes
}
