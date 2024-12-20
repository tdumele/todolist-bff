package com.todolist.bff_todolist.spi.repository;

import com.todolist.bff_todolist.domain.model.user.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    Optional<User> getUserById(UUID uuid);
    User createUser(User user);

    // TODO add cache
    Optional<User> findByUsername(String username);
}
