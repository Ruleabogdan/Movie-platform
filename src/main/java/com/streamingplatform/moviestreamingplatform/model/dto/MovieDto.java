package com.streamingplatform.moviestreamingplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.streamingplatform.moviestreamingplatform.model.Genres;

import java.util.Date;

import lombok.Data;

@Data
public class MovieDto {

    private Long id;
    private String title;
    private Date realeaseDate;
    private Double rating;
    private Genres genres;
    @JsonProperty("user")
    private UserIdDto userIdDto;
}

