package com.alexduzi.blogpostswebflux.Services;

import com.alexduzi.blogpostswebflux.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
