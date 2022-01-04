package com.streamingplatform.moviestreamingplatform.model.dto.mapper;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.RoleDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserIdDto;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ICustomMapper {

    RoleDto roleToRoleDto(Role role);

    List<RoleDto> allRoleToDtoList(List<Role> roles);

    UserIdDto userToUserIdDto(User user);

    UserDto userToUserDto(User user);

    MovieDto movieToMovieDto(Movie movie);

    List<UserDto> listAllUsersDto(List<User> users);

    List<MovieDto> listAllMoviesDto(List<Movie> movies);
}
