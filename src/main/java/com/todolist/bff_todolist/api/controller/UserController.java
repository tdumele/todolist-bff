package com.todolist.bff_todolist.api.controller;

import com.todolist.bff_todolist.api.mapper.UserMapper;
import com.todolist.bff_todolist.api.vo.user.CreateUserRequest;
import com.todolist.bff_todolist.api.vo.user.GetUserResponse;
import com.todolist.bff_todolist.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById(@PathVariable("id") UUID id) {
        var optionalGetUserResponse = userService.getUserById(id).map(userMapper::mapTo);
        return ResponseEntity.of(optionalGetUserResponse);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        var optionalGetUserResponse = userService.createUser(userMapper.mapTo(createUserRequest));
        return ResponseEntity.created(URI.create("/api/v1/users/" + optionalGetUserResponse.getId())).build();
    }

}
