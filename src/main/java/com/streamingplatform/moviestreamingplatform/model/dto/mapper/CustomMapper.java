package com.streamingplatform.moviestreamingplatform.model.dto.mapper;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.RoleDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserIdDto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CustomMapper implements ICustomMapper {

    @Override
    public RoleDto roleToRoleDto(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = new RoleDto();
        roleDto.setName(role.getName());
        return roleDto;
    }

    @Override
    public List<RoleDto> allRoleToDtoList(List<Role> roles) {
        if (roles == null) {
            return null;
        }
        List<RoleDto> roleDtos = new ArrayList<RoleDto>(roles.size());
        for (Role role : roles) {
            roleDtos.add(roleToRoleDto(role));
        }
        return roleDtos;
    }

    @Override
    public UserIdDto userToUserIdDto(User user) {
        if (user == null) {
            return null;
        }
        UserIdDto userIdDto = new UserIdDto();
        userIdDto.setId(user.getId());
        return userIdDto;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setCreationDate(user.getCreationDate());
        userDto.setFavoriteGenre(user.getFavoriteGenre());
        userDto.setRoles(allRoleToDtoList(user.getRoles()));
        return userDto;
    }

    @Override
    public MovieDto movieToMovieDto(Movie movie) {
        if (movie == null) {
            return null;
        }
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setRating(movie.getRating());
        movieDto.setGenres(movie.getGenres());
        movieDto.setRealeaseDate(movie.getRealeaseDate());
        movieDto.setUserIdDto(userToUserIdDto(movie.getUser()));
        return movieDto;
    }

    @Override
    public List<UserDto> listAllUsersDto(List<User> users) {
        if (users == null) {
            return null;
        }
        List<UserDto> list = new ArrayList<UserDto>(users.size());
        for (User user : users) {
            list.add(userToUserDto(user));
        }
        return list;
    }

    @Override
    public List<MovieDto> listAllMoviesDto(List<Movie> movies) {
        if (movies == null) {
            return null;
        }
        List<MovieDto> list = new ArrayList<MovieDto>(movies.size());
        for (Movie movie : movies) {
            list.add(movieToMovieDto(movie));
        }
        return list;
    }
}
