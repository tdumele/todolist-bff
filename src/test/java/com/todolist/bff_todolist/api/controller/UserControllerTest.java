package com.todolist.bff_todolist.api.controller;

import com.todolist.bff_todolist.api.mapper.UserMapperImpl;
import com.todolist.bff_todolist.config.TestsConfiguration;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({UserMapperImpl.class, TestsConfiguration.class})
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService service;

    @Test
    void getUserByIdReturnsUserWhenIdExists() throws Exception {
        UUID uuid = UUID.randomUUID();
        var user = new User();
        user.setId(uuid);


        when(service.getUserById(uuid)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/v1/users/{id}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(uuid.toString()));
    }

    @Test
    void getUserByIdReturnsUserWhenIdDoesNotExist() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(service.getUserById(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/users/{id}", uuid))
                .andExpect(status().isNotFound());
    }
}
