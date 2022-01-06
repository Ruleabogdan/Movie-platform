package com.streamingplatform.moviestreamingplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamingplatform.moviestreamingplatform.MovieStreamingPlatformApplication;
import com.streamingplatform.moviestreamingplatform.model.Genres;
import com.streamingplatform.moviestreamingplatform.model.Movie;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MovieStreamingPlatformApplication.class})
@AutoConfigureMockMvc(addFilters = false)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAllFUnc() throws Exception {
    }

    @Test
    void testGetMovies() throws Exception {
        String result
                = "[{\"id\":1,\"title\":\"Home Alone\",\"realeaseDate\":\"3893-01-12\",\"rating\":7.1,\"genres\":\"COMEDY\",\"user\":{\"id\":1}},"
                + "{\"id\":2,\"title\":\"Castelvania\",\"realeaseDate\":\"3911-01-21\",\"rating\":7.1,\"genres\":\"COMEDY\",\"user\":{\"id\":1}}]";
        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(content().string(result));
    }

    @Test
    void testAddMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/movies")
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON)
                                              .content(
                                                      asJsonString(new Movie(null, "Castelvania", new Date(2010, 12, 21), 7.1, Genres.COMEDY, null))))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id")
                                  .exists())
               .andExpect(jsonPath("$.title")
                                  .value("Castelvania"
                                  ))
               .andExpect(jsonPath("$.rating")
                                  .value(7.1));
    }

    @Test
    void testGetMovieById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/1")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id")
                                  .value(1))
               .andExpect(jsonPath("$.title")
                                  .value("Home Alone"))
               .andExpect(jsonPath("$.rating")
                                  .value(7.1));
    }

    @Test
    void testDeleteMovieById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/movies/1")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1))
               .andExpect(jsonPath("$.title").value("Home Alone"))
               .andExpect(jsonPath("$.rating").value(7.1));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}