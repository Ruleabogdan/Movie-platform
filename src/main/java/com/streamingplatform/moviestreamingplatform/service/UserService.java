package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;

import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserDto;
import com.streamingplatform.moviestreamingplatform.model.dto.mapper.ImyMapper;
import com.streamingplatform.moviestreamingplatform.repository.MovieRepository;
import com.streamingplatform.moviestreamingplatform.repository.RoleRepository;
import com.streamingplatform.moviestreamingplatform.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private MovieRepository movieRepository;
    private ImyMapper myMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role to database: {}", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String userName,
                              String roleName) {
        User user = userRepository.findByUsername(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles()
            .add(role);
        return user;
    }

    @Override
    public UserDto saveUser(User theUser) {
        theUser.setId(null);
        theUser.setCreation_date(new Date(System.currentTimeMillis()));
        theUser.setPassword(passwordEncoder.encode(theUser.getPassword()));
        userRepository.save(theUser);
        log.info("Saving new user to database: {}", theUser.getUsername());
        return myMapper.userToUserDto(userRepository.getById(theUser.getId()));
    }

    @Override
    public List<UserDto> findAll() {
        return myMapper.listAllUsersDto(userRepository.findAll());
    }

    @Override
    public UserDto deleteById(long userId) {
        User theUser = userRepository.findById(userId)
                                     .get();
        userRepository.deleteById(userId);
        return myMapper.userToUserDto(theUser);
    }

    @Override
    public UserDto findById(long userId) {
        Optional<User> result = userRepository.findById(userId);
        User theUser = null;
        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + userId);
        }
        return myMapper.userToUserDto(theUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles()
            .forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public MovieDto addMovieToWatchlist(long userId,
                                        long movieId) {
        User user = userRepository.getById(userId);
        Movie movie = movieRepository.getById(movieId);
        user.getMovieCollection()
            .add(movie);
        return myMapper.movieToMovieDto(movie);
    }

    @Override
    public List<MovieDto> showWatchlist(long userId) {
        User user = userRepository.getById(userId);
        return myMapper.listAllMoviesDto(user.getMovieCollection());
    }

    @Override
    public MovieDto deleteMovieFromWatchlist(long userId,
                                             long movieId) {
        User user = userRepository.getById(userId);
        Movie movie = movieRepository.getById(movieId);
        user.getMovieCollection()
            .remove(movie);
        return myMapper.movieToMovieDto(movie);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());
        return user;
    }
}


