package com.streamingplatform.moviestreamingplatform.controller;

import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.dto.MovieDto;
import com.streamingplatform.moviestreamingplatform.model.dto.UserDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/register")
    public UserDto addUser(@RequestBody User theUser) {
        return userService.saveUser(theUser);
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable long userId) {
        return userService.findById(userId);
    }

    @DeleteMapping("/{userId}")
    public UserDto deleteUser(@PathVariable long userId) {
        return userService.deleteById(userId);
    }

    @PostMapping("/add-role")
    public UserDto addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        return userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
    }

    @PostMapping("/{userId}/watchlist/{movieId}")
    public MovieDto addMovieToWatchlist(@PathVariable long userId,
                                        @PathVariable long movieId) {
        if (userService.getCurrentUser()
                       .getId() != userId) {
            throw new RuntimeException("You cannot add a movie into other's watchlist");
        } else {
            return userService.addMovieToWatchlist(userId, movieId);
        }
    }

    @GetMapping("/{userId}/watchlist")
    public List<MovieDto> showUserWatchlist(@PathVariable long userId) {
        return userService.showWatchlist(userId);
    }

    @DeleteMapping("/{userId}/watchlist/{movieId}")
    public MovieDto deleteMovieFromWatchlist(@PathVariable long userId,
                                             @PathVariable long movieId) {
        if (userService.getCurrentUser()
                       .getId() != userId) {
            throw new RuntimeException("You cannot delete a movie from other's watchlist");
        } else {
            return userService.deleteMovieFromWatchlist(userId, movieId);
        }
    }

    @GetMapping("/token/refresh")
    private void refreshToken(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm)
                                          .build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String accessToken = JWT.create()
                                        .withSubject(user.getUsername())
                                        .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                                        .withIssuer(request.getRequestURL()
                                                           .toString())
                                        .withClaim("roles", user.getRoles()
                                                                .stream()
                                                                .map(Role::getName)
                                                                .collect(Collectors.toList()))
                                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing.");
        }
    }
}

@Data
class RoleToUserForm {

    private String username;
    private String roleName;
}
