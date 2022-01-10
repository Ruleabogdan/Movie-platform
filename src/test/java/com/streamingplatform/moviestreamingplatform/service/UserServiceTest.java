package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Genres;
import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserDto;
import com.streamingplatform.moviestreamingplatform.model.dto.mapper.CustomMapper;
import com.streamingplatform.moviestreamingplatform.repository.MovieRepository;
import com.streamingplatform.moviestreamingplatform.repository.RoleRepository;
import com.streamingplatform.moviestreamingplatform.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.streamingplatform.moviestreamingplatform.model.Genres.COMEDY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    CustomMapper customMapper;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MovieRepository movieRepository;
    private User user = new User();
    private UserDto userDto = new UserDto();
    private List<UserDto> userDtoList = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Movie movie = new Movie();
    private MovieDto movieDto = new MovieDto();
    private List<MovieDto> movieDtoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        when(customMapper.userToUserDto(any())).thenReturn(userDto);
        userDto.setId(1L);
        userDto.setUsername("marcelpavel");
        userDto.setCreationDate(new Date(System.currentTimeMillis()));
        userDto.setFavoriteGenre(Genres.ACTION);
        userDtoList.add(userDto);
        users = new ArrayList<>();
        Date currentDate = new Date(System.currentTimeMillis());
        user = new User();
        user.setId(1L);
        user.setUsername("marcelpavel");
        user.setPassword("12345");
        user.setCreationDate(currentDate);
        user.setFavoriteGenre(Genres.ACTION);
        users.add(user);
        movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Home Alone");
        movie.setGenres(COMEDY);
        movie.setRating(7.1);
        movie.setRealeaseDate(new Date(1992, 12, 12));
        movieDto = new MovieDto();
        movieDto.setId(1L);
        movieDto.setGenres(COMEDY);
        movieDto.setTitle("Home Alone");
        movieDto.setRating(7.1);
        movieDto.setRealeaseDate(new Date(1992, 12, 12));
        user.getMovieCollection()
            .add(movie);
        movieDtoList = new ArrayList<>();
        movieDtoList.add(movieDto);
    }

    @Test
    void testGetUser() {
        when(userRepository.findByUsername("marcelpavel")).thenReturn(user);
        User theUser = userService.getUser("marcelpavel");
        assertNotNull(theUser);
        assertEquals(user.getUsername(), theUser.getUsername());
        assertEquals(user.getFavoriteGenre(), theUser.getFavoriteGenre());
        assertEquals(user.getPassword(), theUser.getPassword());
        assertEquals(user.getId(), theUser.getId());
        assertEquals(user.getCreationDate(), theUser.getCreationDate());
        verify(userRepository, times(1)).findByUsername("marcelpavel");
    }

    @Test
    void testSaveRole() {
        Role role = new Role(1L, "USER");
        when(roleRepository.save(role)).thenReturn(role);
        Role role1 = roleRepository.save(role);
        assertNotNull(role1);
        assertEquals(role.getName(), role1.getName());
        verify(roleRepository, times(1)).save(role);
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(any())).thenReturn(user);
        UserDto userDto = userService.saveUser(user);
        assertNotNull(userDto);
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getId(), userDto.getId());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testFindAll() {
        when(userRepository.findAll()).thenReturn(users);
        when(customMapper.listAllUsersDto(users)).thenReturn(userDtoList);
        userDtoList = userService.findAll();
        assertEquals(1, userDtoList.size());
        assertEquals(userDtoList.get(0)
                                .getUsername(), user.getUsername());
        assertEquals(userDtoList.get(0)
                                .getFavoriteGenre(), user.getFavoriteGenre());
        assertEquals(userDtoList.get(0)
                                .getId(), user.getId());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        UserDto userDto = userService.deleteById(user.getId());
        verify(userRepository, times(1)).deleteById(user.getId());
        assertNotNull(userDto);
    }

    @Test
    void testFindById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        UserDto userDto = userService.findById(1);
        assertNotNull(userDto);
        assertEquals(userDto.getUsername(), user.getUsername());
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void addMovieToWatchlist() {
        when(userRepository.getById(anyLong())).thenReturn(user);
        when(movieRepository.getById(anyLong())).thenReturn(movie);
        when(customMapper.movieToMovieDto(any())).thenReturn(movieDto);
        MovieDto result = userService.addMovieToWatchlist(user.getId(), movie.getId());
        assertEquals(movie.getTitle(), movieDto.getTitle());
        assertEquals(movie.getGenres(), movieDto.getGenres());
        assertEquals(movie.getId(), movieDto.getId());
        assertEquals(movie.getRating(), movieDto.getRating());
        verify(userRepository, times(1)).getById(user.getId());
        verify(movieRepository, times(1)).getById(movie.getId());
    }

    @Test
    void showWatchlist() {
        when(userRepository.getById(anyLong())).thenReturn(user);
        when(customMapper.listAllMoviesDto(anyList())).thenReturn(movieDtoList);
        List<MovieDto> result = userService.showWatchlist(1L);
        assertEquals(1, result.size());
        assertEquals(movie.getId(), result.get(0)
                                          .getId());
        assertEquals(movie.getTitle(), result.get(0)
                                             .getTitle());
        assertEquals(movie.getRating(), result.get(0)
                                              .getRating());
    }

    @Test
    void deleteMovieFromWatchlist() {
        when(userRepository.getById(anyLong())).thenReturn(user);
        when(movieRepository.getById(anyLong())).thenReturn(movie);
        when(customMapper.movieToMovieDto(any())).thenReturn(movieDto);
        MovieDto deletedMovie = userService.deleteMovieFromWatchlist(user.getId(), movie.getId());
        assertNotNull(deletedMovie);
        assertEquals(movie.getId(), deletedMovie.getId());
        assertEquals(movie.getTitle(), deletedMovie.getTitle());
        assertEquals(movie.getRating(), deletedMovie.getRating());
        verify(userRepository, times(1)).getById(1L);
        verify(userRepository, times(1)).getById(1L);
    }
}