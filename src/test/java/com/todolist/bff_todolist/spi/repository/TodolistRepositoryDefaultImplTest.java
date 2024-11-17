package com.todolist.bff_todolist.spi.repository;

import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.spi.mapper.TodolistSpiMapperImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Sql("/data.sql")
class TodolistRepositoryTest {

    @Autowired
    private TodolistRepository todolistRepository;

    @Test
    void getAllTodolistsReturnsListOfTodolists() {
        List<Todolist> actualTodolists = todolistRepository.getAllTodolists();

        assertEquals(3, actualTodolists.size());
    }

    @Test
    void getTodolistByIdReturnsTodolistWhenIdExists() {
        UUID id = UUID.fromString("a4e310d3-a741-4751-ab54-f378a72e9870");

        Optional<Todolist> actualTodolist = todolistRepository.getTodolistById(id);

        assertTrue(actualTodolist.isPresent());
        assertEquals("Learn Spring Boot", actualTodolist.get().getTitle());
    }

    @Test
    void getTodolistByIdReturnsEmptyWhenIdDoesNotExist() {
        UUID id = UUID.randomUUID();

        Optional<Todolist> actualTodolist = todolistRepository.getTodolistById(id);

        assertTrue(actualTodolist.isEmpty());
    }

    @TestConfiguration
    @Import({
            TodolistRepositoryDefaultImpl.class, TodolistSpiMapperImpl.class
    })
    static class Config{

    }
}