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

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @GetMapping("/")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping("/")
    public User addUser(@RequestBody User theUser) {
        return userService.save(theUser);
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
}
