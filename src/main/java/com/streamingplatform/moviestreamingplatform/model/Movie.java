package com.streamingplatform.moviestreamingplatform.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "movie")
@Getter @Setter @NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "realease_date")
    private Date realeaseDate;

    @Column(name = "rating")
    private Double rating;

    public Movie(String title,
                 Date realeaseDate,
                 Double rating) {
        this.title = title;
        this.realeaseDate = realeaseDate;
        this.rating = rating;
    }
}
