package com.streamingplatform.moviestreamingplatform.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.User;
<<<<<<< Updated upstream
=======
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserDto;
import com.streamingplatform.moviestreamingplatform.model.dto.mapper.ImyMapper;
import com.streamingplatform.moviestreamingplatform.repository.MovieRepository;
import com.streamingplatform.moviestreamingplatform.repository.RoleRepository;
>>>>>>> Stashed changes
import com.streamingplatform.moviestreamingplatform.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;
<<<<<<< Updated upstream

    @Override
    public User save(User theUser) {
        theUser.setId(0);
=======
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
>>>>>>> Stashed changes
        theUser.setCreationDate(new Date(System.currentTimeMillis()));
        userRepository.save(theUser);
<<<<<<< Updated upstream
        return userRepository.getById(theUser.getId());
=======
        log.info("Saving new user to database: {}", theUser.getUsername());
        return myMapper.userToUserDto(userRepository.getById(theUser.getId()));
>>>>>>> Stashed changes
    }

    @Override
    public List<UserDto> findAll() {
        return myMapper.listAllUsersDto(userRepository.findAll());
    }

    @Override
<<<<<<< Updated upstream
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public User deleteById(long userId) {
=======
    public UserDto deleteById(long userId) {
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
=======

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
>>>>>>> Stashed changes
}


