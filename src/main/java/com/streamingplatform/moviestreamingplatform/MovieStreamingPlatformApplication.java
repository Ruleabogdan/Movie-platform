package com.streamingplatform.moviestreamingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.service.UserService;

import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

import static com.streamingplatform.moviestreamingplatform.model.Genres.ACTION;
import static com.streamingplatform.moviestreamingplatform.model.Genres.COMEDY;
import static com.streamingplatform.moviestreamingplatform.model.Genres.DRAMA;

@SpringBootApplication
public class MovieStreamingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieStreamingPlatformApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveUser(new User(null, "vlad", "12345", null, ACTION, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "marc", "12345", null, COMEDY, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userService.saveUser(new User(null, "florin", "12345", null, DRAMA, new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userService.addRoleToUser("vlad", "ROLE_USER");
            userService.addRoleToUser("marc", "ROLE_ADMIN");
            userService.addRoleToUser("florin", "ROLE_USER");
        };
    }
}
