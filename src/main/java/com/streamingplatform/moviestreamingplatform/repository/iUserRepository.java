package com.streamingplatform.moviestreamingplatform.repository;

import com.streamingplatform.moviestreamingplatform.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface iUserRepository extends JpaRepository<User, Long> {

}
