package com.streamingplatform.moviestreamingplatform.repository;

import com.streamingplatform.moviestreamingplatform.model.Watchlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {
}
