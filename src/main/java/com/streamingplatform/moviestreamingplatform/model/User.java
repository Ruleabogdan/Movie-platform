package com.streamingplatform.moviestreamingplatform.model;

<<<<<<< Updated upstream
=======
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Collection;
>>>>>>> Stashed changes
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date")
    private Date creationDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "favorite_genre")
    private Genres favoriteGenre;
<<<<<<< Updated upstream

    public User(String username,
                String password,
                Date creationDate,
                Genres favoriteGenre) {
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
        this.favoriteGenre = favoriteGenre;
=======
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "user_watchlist", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movieCollection = new ArrayList<>();
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Collection<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        if (movies == null) {
            movies = new ArrayList<>();
        }
        movie.setUser(this);
        movies.add(movie);
>>>>>>> Stashed changes
    }
}
