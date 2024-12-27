package com.todolist.bff_todolist.domain.service;

import com.todolist.bff_todolist.domain.model.CreateTaskRequest;
import com.todolist.bff_todolist.domain.model.Task;
import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.domain.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodolistService {

    List<Todolist> getAllTodolists();

    Optional<Todolist> getTodolistById(UUID id);

    List<Task> getTasksFromTodolist(UUID id);

    List<Todolist> getAllTodolistsByUser(User user);

    Task createTaskInTodolist(UUID id, CreateTaskRequest request);
}
