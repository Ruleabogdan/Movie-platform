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

    private Long id;
    private String username;
    private Date creationDate;
    private Genres favoriteGenre;
    private List<RoleDto> roles;
}
