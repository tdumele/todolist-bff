package com.todolist.bff_todolist.api.controller;

import com.todolist.bff_todolist.api.mapper.TodolistMapperImpl;
import com.todolist.bff_todolist.domain.model.STATUS;
import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.domain.service.TodolistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
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

@WebMvcTest(TodolistController.class)
class TodolistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodolistService service;

    @Test
    void getAllTodolistsReturnsListOfTodolists() throws Exception {
        UUID uuid = UUID.randomUUID();
        var todolist = new Todolist();
        todolist.setId(uuid);
        todolist.setTitle("title");
        todolist.setDescription("description");
        todolist.setStatus(STATUS.IN_PROGRESS);

        when(service.getAllTodolists()).thenReturn(List.of(todolist));

        mockMvc.perform(get("/api/v1/todolists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(uuid.toString()))
                .andExpect(jsonPath("$[0].title").value("title"))
                .andExpect(jsonPath("$[0].description").value("description"))
                .andExpect(jsonPath("$[0].status").value("IN_PROGRESS"));
    }

    @Test
    void getTodolistByIdReturnsTodolistWhenIdExists() throws Exception {
        UUID uuid = UUID.randomUUID();
        var todolist = new Todolist();
        todolist.setId(uuid);
        todolist.setTitle("title");
        todolist.setDescription("description");
        todolist.setStatus(STATUS.IN_PROGRESS);

        when(service.getTodolistById(uuid)).thenReturn(Optional.of(todolist));

        mockMvc.perform(get("/api/v1/todolists/{id}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(uuid.toString()))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.description").value("description"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void getTodolistByIdReturnsTodolistWhenIdDoesntExist() throws Exception {
        UUID uuid = UUID.randomUUID();
        when(service.getTodolistById(any(UUID.class))).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/todolists/{id}", uuid))
                .andExpect(status().isNotFound());
    }

    @TestConfiguration
    @Import({TodolistMapperImpl.class})
    static class Config {

    }
}