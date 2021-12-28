package com.streamingplatform.moviestreamingplatform.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.streamingplatform.moviestreamingplatform.model.Genres;
import com.streamingplatform.moviestreamingplatform.model.Role;
import com.streamingplatform.moviestreamingplatform.model.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
public class UserDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("username")
    private String username;
    @JsonProperty("creationDate")
    private Date creationDate;
    @JsonProperty("favoriteGenre")
    private Genres favoriteGenre;
    @JsonProperty("roles")
    private List<RoleDto> roles;
}
