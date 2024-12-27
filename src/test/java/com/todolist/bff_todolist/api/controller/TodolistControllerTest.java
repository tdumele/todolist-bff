package com.todolist.bff_todolist.api.controller;

import com.todolist.bff_todolist.api.mapper.TaskMapperImpl;
import com.todolist.bff_todolist.config.TestsConfiguration;
import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.domain.service.TodolistService;
import com.todolist.bff_todolist.security.JwtService;
import com.todolist.bff_todolist.spi.dao.UserDAO;
import com.todolist.bff_todolist.spi.mapper.UserSpiMapperImpl;
import com.todolist.bff_todolist.spi.repository.impl.UserRepositoryDefaultImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TaskMapperImpl.class, TestsConfiguration.class, JwtService.class, UserRepositoryDefaultImpl.class, UserSpiMapperImpl.class})
@WebMvcTest(TodolistController.class)
@Disabled("This test requires a valid token")
class TodolistControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodolistService service;

    @MockBean
    UserDAO userDAO;

    @Test
    void getAllTodolistsReturnsListOfTodolists() throws Exception {
        UUID uuid = UUID.randomUUID();
        var todolist = new Todolist(uuid, "title", "description", null);
        when(service.getAllTodolists()).thenReturn(List.of(todolist));

        mockMvc.perform(get("/api/v1/todolists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(uuid.toString()))
                .andExpect(jsonPath("$[0].title").value("title"))
                .andExpect(jsonPath("$[0].description").value("description"));
    }

    @Test
    void getTodolistByIdReturnsTodolistWhenIdExists() throws Exception {
        UUID uuid = UUID.randomUUID();
        var todolist = new Todolist(uuid, "title", "description", null);

        when(service.getTodolistById(uuid)).thenReturn(Optional.of(todolist));

        mockMvc.perform(get("/api/v1/todolists/{id}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.description").value("description"));
    }

    @Test
    void getTodolistByIdReturnsTodolistWhenIdDoesNotExist() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(service.getTodolistById(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/todolists/{id}", uuid))
                .andExpect(status().isNotFound());
    }
}