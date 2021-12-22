package com.streamingplatform.moviestreamingplatform.controller;

import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User theUser) {
        // if they pass an id in JSON
        theUser.setId(0);
        // set the current date
        theUser.setCreationDate(new Date(System.currentTimeMillis()));
        userService.save(theUser);
        return theUser;
    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable long userId) {
        User theUser = userService.findById(userId);
        return theUser;
    }

    @DeleteMapping("/users/{userId}")
    public User deleteUser(@PathVariable long userId) {
        User theUser = userService.findById(userId);
        userService.deleteById(userId);
        return theUser;
    }
}
