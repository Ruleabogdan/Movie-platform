package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.User;

import java.util.List;

public interface iUserService {
    public void save(User theUser);

    public List<User> findAll();

    public void deleteById(long userId);

    public User findById(long userId);
}
