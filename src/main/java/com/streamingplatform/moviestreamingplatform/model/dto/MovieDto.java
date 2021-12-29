package com.streamingplatform.moviestreamingplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.streamingplatform.moviestreamingplatform.model.Genres;

import java.util.Date;

import lombok.Data;

@Data
public class MovieDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("realeaseDate")
    private Date releaseDate;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("genres")
    private Genres genres;
    @JsonProperty("user")
    private UserIdDto userIdDto;
}

