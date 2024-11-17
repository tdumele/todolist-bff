package com.todolist.bff_todolist.domain.service.impl;

import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.spi.repository.TodolistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodolistServiceDefaultImplTest {

    @Mock
    private TodolistRepository todolistRepository;

    @InjectMocks
    private TodolistServiceDefaultImpl todolistService;

    @Test
    void getAllTodolistsReturnsListOfTodolists() {
        List<Todolist> expectedTodolists = List.of(new Todolist(), new Todolist());
        when(todolistRepository.getAllTodolists()).thenReturn(expectedTodolists);

        List<Todolist> actualTodolists = todolistService.getAllTodolists();

        assertEquals(expectedTodolists, actualTodolists);
    }

    @Test
    void getTodolistByIdReturnsTodolistWhenIdExists() {
        UUID id = UUID.randomUUID();
        Todolist expectedTodolist = new Todolist();
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.of(expectedTodolist));

        Optional<Todolist> actualTodolist = todolistService.getTodolistById(id);

        assertTrue(actualTodolist.isPresent());
        assertEquals(expectedTodolist, actualTodolist.get());
    }

    @Test
    void getTodolistByIdReturnsEmptyWhenIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.empty());

        Optional<Todolist> actualTodolist = todolistService.getTodolistById(id);

        assertTrue(actualTodolist.isEmpty());
    }
}