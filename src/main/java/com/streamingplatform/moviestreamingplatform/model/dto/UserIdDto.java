package com.streamingplatform.moviestreamingplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserIdDto {

    @JsonProperty("id")
    private Long id;
}
