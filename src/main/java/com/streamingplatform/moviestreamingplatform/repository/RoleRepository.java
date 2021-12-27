package com.streamingplatform.moviestreamingplatform.repository;

import com.streamingplatform.moviestreamingplatform.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
