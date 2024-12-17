package com.todolist.bff_todolist.domain.service.impl;

import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.service.UserService;
import com.todolist.bff_todolist.spi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceDefaultImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> getUserById(UUID uuid) {
        return userRepository.getUserById(uuid);
    }
}
