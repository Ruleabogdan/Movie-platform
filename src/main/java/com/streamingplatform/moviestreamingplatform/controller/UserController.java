package com.streamingplatform.moviestreamingplatform.controller;

import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.service.IUserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private IUserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping
    public User addUser(@RequestBody User theUser) {
        return userService.saveUser(theUser);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable long userId) {
        User theUser = userService.findById(userId);
        return theUser;
    }

    @DeleteMapping("/{userId}")
    public User deleteUser(@PathVariable long userId) {
        User theUser = userService.deleteById(userId);
        return theUser;
    }

    @PostMapping("/add-role")
    public User addRoleToUser(@RequestBody RoleToUserForm roleToUserForm) {
        return userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRoleName());
    }

    @PostMapping("/watchlist/{movieId}")
    public Movie addMovieToWatchlist(@PathVariable long movieId){
        return userService.addMovieToWatchlist(movieId);
    }

    @GetMapping("/watchlist")
    public Collection<Movie> showUserWatchlist(){
        return userService.showWatchlist();
    }

    @DeleteMapping("/watchlist/{movieId}")
    public Movie deleteMovieFromWatchlist(@PathVariable long movieId){
        return userService.deleteMovieFromWatchlist(movieId);
    }

}

@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
