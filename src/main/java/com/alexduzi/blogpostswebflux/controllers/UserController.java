package com.alexduzi.blogpostswebflux.controllers;

import com.alexduzi.blogpostswebflux.models.dto.UserDTO;
import com.alexduzi.blogpostswebflux.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Flux<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<ResponseEntity<UserDTO>> findById(@PathVariable String id) {
        return userService.findById(id).map(ResponseEntity::ok);
    }

    @PostMapping
    public Mono<ResponseEntity<UserDTO>> insert(@RequestBody UserDTO userDto, UriComponentsBuilder builder) {
        return userService.insert(userDto)
                .map(newUser -> ResponseEntity
                        .created(builder.path("/users/{id}")
                        .buildAndExpand(newUser.getId()).toUri()).body(newUser));
    }
}
