package com.streamingplatform.moviestreamingplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamingplatform.moviestreamingplatform.MovieStreamingPlatformApplication;
import com.streamingplatform.moviestreamingplatform.model.Genres;
import com.streamingplatform.moviestreamingplatform.model.User;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MovieStreamingPlatformApplication.class})
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUsers() throws Exception {
        String result = "[{\"id\":1,\"username\":\"vlad\",\"creationDate\":\"2022-01-06\",\"favoriteGenre\":\"ACTION\","
                + "\"roles\":[{\"name\":\"ROLE_USER\"}]},{\"id\":2,\"username\":\"marc\",\"creationDate\":\"2022-01-06\","
                + "\"favoriteGenre\":\"COMEDY\",\"roles\":[{\"name\":\"ROLE_ADMIN\"}]},{\"id\":3,\"username\":\"florin\","
                + "\"creationDate\":\"2022-01-06\",\"favoriteGenre\":\"DRAMA\",\"roles\":[{\"name\":\"ROLE_USER\"}]}]";
        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().string(result));
    }

    @Test
    void testAddUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                                              .content(asJsonString(new User(null, "georgecosmin", "12345", null, Genres.ACTION, new ArrayList<>(),
                                                                             new ArrayList<>(), new ArrayList<>())))
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                                               .exists())
               .andExpect(MockMvcResultMatchers.jsonPath("$.username")
                                               .value("georgecosmin"));
    }

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1")
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                                               .value(1))
               .andExpect(MockMvcResultMatchers.jsonPath("$.username")
                                               .value("vlad"))
               .andDo(print());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/2"))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                                               .value(2))
               .andExpect(MockMvcResultMatchers.jsonPath("$.username")
                                               .value("marc"));
    }

    @Test
    void testAddRoleToUser() throws Exception {
        RoleToUserForm roleToUserForm = new RoleToUserForm();
        roleToUserForm.setUsername("florin");
        roleToUserForm.setRoleName("ROLE_ADMIN");
        mockMvc.perform(MockMvcRequestBuilders.post("/users/add-role")
                                              .content(asJsonString(roleToUserForm))
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.id")
                                               .value(3))
               .andExpect(MockMvcResultMatchers.jsonPath("$.username")
                                               .value("florin"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.roles")
                                               .isArray());
    }

    @Test
    void testShowUserWatchlist() throws Exception {
        String result
                = "[{\"id\":1,\"title\":\"Home Alone\",\"realeaseDate\":\"3893-01-12\",\"rating\":7.1,\"genres\":\"COMEDY\",\"user\":{\"id\":1}}]";
        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/watchlist")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(content().string(result));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}