package com.alexduzi.blogpostswebflux.services;

import com.alexduzi.blogpostswebflux.exceptions.ResourceNotFoundException;
import com.alexduzi.blogpostswebflux.models.dto.UserDTO;
import com.alexduzi.blogpostswebflux.repositories.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<UserDTO> findAll() {
        return userRepository.findAll().map(UserDTO::new);
    }

    public Mono<UserDTO> findById(String id) {
        return userRepository.findById(id)
                .map(UserDTO::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("User not found!")));
    }
}
