package com.streamingplatform.moviestreamingplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RoleDto {

    @JsonProperty("name")
    private String name;
}
