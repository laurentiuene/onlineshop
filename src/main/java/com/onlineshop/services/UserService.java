package com.onlineshop.services;

import com.onlineshop.models.User;
import com.onlineshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
