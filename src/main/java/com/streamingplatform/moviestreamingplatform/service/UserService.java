package com.streamingplatform.moviestreamingplatform.service;

import com.streamingplatform.moviestreamingplatform.model.User;
import com.streamingplatform.moviestreamingplatform.repository.iUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements iUserService{

    private iUserRepository userRepository;

    @Autowired
    public UserService(iUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(long userId) {
        userRepository.deleteById(userId);
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


