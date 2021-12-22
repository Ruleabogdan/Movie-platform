package com.streamingplatform.moviestreamingplatform.repository;

import com.streamingplatform.moviestreamingplatform.model.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
