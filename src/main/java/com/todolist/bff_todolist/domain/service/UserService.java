package com.todolist.bff_todolist.domain.service;

import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.model.user.bo.CreateUser;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> getUserById(UUID uuid);
    User createUser(CreateUser createUser);
}
