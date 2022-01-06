package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.Genres;
import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserIdDto;
import com.streamingplatform.moviestreamingplatform.model.dto.mapper.CustomMapper;
import com.streamingplatform.moviestreamingplatform.repository.MovieRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private CustomMapper customMapper;
    private UserIdDto userIdDto = new UserIdDto();
    private User user = new User();
    private Movie movie = new Movie();
    private MovieDto movieDto = new MovieDto();
    private List<MovieDto> movieDtoList = new ArrayList<>();
    private List<Movie> movieList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        when(customMapper.userToUserIdDto(any())).thenReturn(userIdDto);
        when(customMapper.movieToMovieDto(any())).thenReturn(movieDto);
        userIdDto.setId(1L);
        movieDto.setId(1L);
        movieDto.setGenres(Genres.COMEDY);
        movieDto.setRating(7.1);
        movieDto.setTitle("Home Alone");
        movieDto.setRealeaseDate(new Date(1994, 12, 12));
        movieDto.setUserIdDto(userIdDto);
        movieDtoList.add(movieDto);
        user.setId(1L);
        movie.setId(1L);
        movie.setGenres(Genres.ACTION);
        movie.setRating(7.1);
        movie.setRealeaseDate(new Date(1994, 12, 12));
        movie.setTitle("Home Alone");
        movie.setUser(user);
        movieList.add(movie);
    }

    @Test
    void testFindById() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        MovieDto result = movieService.findById(1L);
        assertNotNull(result);
        assertEquals(movie.getId(), result.getId());
        assertEquals(movie.getTitle(), result.getTitle());
        verify(movieRepository, times(1)).findById(movie.getId());
    }

    @Test
    void testDeleteById() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        MovieDto result = movieService.deleteById(movie.getId());
        assertNotNull(result);
    }

    @Test
    void testFindAll() {
        when(movieRepository.findAll()).thenReturn(movieList);
        when(customMapper.listAllMoviesDto(movieList)).thenReturn(movieDtoList);
        List<MovieDto> result = movieService.findAll();
        assertNotNull(result);
    }
}