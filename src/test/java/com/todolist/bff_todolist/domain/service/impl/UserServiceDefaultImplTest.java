package com.todolist.bff_todolist.domain.service.impl;

import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.spi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceDefaultImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceDefaultImpl userService;

    @Test
    void getUserById_returnsUser_whenUserExists() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        when(userRepository.getUserById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void getUserById_returnsEmpty_whenUserDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userRepository.getUserById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(userId);

        assertTrue(result.isEmpty());
    }
}