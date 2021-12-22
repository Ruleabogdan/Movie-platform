package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.User;

import java.util.List;

public interface IUserService {
    public User save(User theUser);

    public List<User> findAll();

    public User deleteById(long userId);

    public User findById(long userId);
}
