package com.streamingplatform.moviestreamingplatform.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Override
    public User save(User theUser) {
        theUser.setId(0);

        theUser.setCreationDate(new Date(System.currentTimeMillis()));

        userRepository.save(theUser);

        return userRepository.getById(theUser.getId());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public User deleteById(long userId)
    {
        User theUser = userRepository.findById(userId).get();

        userRepository.deleteById(userId);

        return theUser;
    }

    @Override
    public User findById(long userId) {
        Optional<User> result = userRepository.findById(userId);

        User theUser = null;

        if(result.isPresent()){
            theUser = result.get();
        }
        else{
            throw new RuntimeException("Did not find employee id - " + userId);
        }

        return theUser;
    }
}


