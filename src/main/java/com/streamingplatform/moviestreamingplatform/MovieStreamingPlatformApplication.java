package com.streamingplatform.moviestreamingplatform;

<<<<<<< Updated upstream
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
=======
import com.streamingplatform.moviestreamingplatform.model.Movie;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;

import static com.streamingplatform.moviestreamingplatform.model.Genres.ACTION;
import static com.streamingplatform.moviestreamingplatform.model.Genres.COMEDY;
import static com.streamingplatform.moviestreamingplatform.model.Genres.DRAMA;
>>>>>>> Stashed changes

@SpringBootApplication
public class MovieStreamingPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieStreamingPlatformApplication.class, args);
    }
}
