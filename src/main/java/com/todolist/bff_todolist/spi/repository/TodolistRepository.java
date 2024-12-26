package com.todolist.bff_todolist.spi.repository;

import com.todolist.bff_todolist.domain.model.Task;
import com.todolist.bff_todolist.domain.model.Todolist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodolistRepository {

    List<Todolist> getAllTodolists();

    Optional<Todolist> getTodolistById(UUID id);

    List<Task> getTasksFromTodolist(UUID id);

    List<Todolist> getAllTodolistsByUserId(UUID id);
}
