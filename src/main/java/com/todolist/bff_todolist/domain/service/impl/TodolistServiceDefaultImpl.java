package com.todolist.bff_todolist.domain.service.impl;

import com.todolist.bff_todolist.domain.model.Task;
import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.service.TodolistService;
import com.todolist.bff_todolist.spi.repository.TodolistRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TodolistServiceDefaultImpl implements TodolistService {

    private final TodolistRepository todolistRepository;

    @Override
    public List<Todolist> getAllTodolists() {
        return todolistRepository.getAllTodolists();
    }

    @Override
    public Optional<Todolist> getTodolistById(UUID id) {
        return todolistRepository.getTodolistById(id);
    }

    @Override
    public List<Task> getTasksFromTodolist(UUID id) {
        return todolistRepository.getTasksFromTodolist(id);
    }

    @Override
    public List<Todolist> getAllTodolistsByUser(User user) {
        return todolistRepository.getAllTodolistsByUserId(user.getId());
    }

    @Override
    public Task createTaskInTodolist(UUID idTodolist, Task request) {
        Todolist todolist = todolistRepository.getTodolistById(idTodolist).orElseThrow(() -> new IllegalStateException("Todolist not found"));
        checkPostTaskRequest(todolist, request);
        Task task = new Task(UUID.randomUUID(), request.title(), request.description(), false, request.dueDate(), todolist);

        return todolistRepository.createTask(task);
    }

    @Override
    public Task updateTaskInTodolist(UUID idTodolist, Task task) {
        Todolist todolist = todolistRepository.getTodolistById(idTodolist).orElseThrow(() -> new IllegalStateException("Todolist not found"));
        checkPutTaskRequest(todolist, task);
        // TODO revise the MCD
        var preparedTask = new Task(task.id(), task.title(), task.description(), task.checked(), task.dueDate(), todolist);
        return todolistRepository.updateTask(preparedTask);
    }

    private void checkPutTaskRequest(Todolist todolist, Task request) {
        if (request.id() == null) {
            throw new IllegalArgumentException("Task id is required");
        }
        if (StringUtils.isBlank(request.title())) {
            throw new IllegalArgumentException("Title is required");
        }
        if (loggerUserDoesntOwnThis(todolist)) {
            throw new IllegalArgumentException("User does not own this todolist");
        }
        todolistRepository.findTaskById(request.id()).orElseThrow(() -> new IllegalStateException("Task not found"));
    }

    private void checkPostTaskRequest(Todolist todolist, Task request) {
        if (StringUtils.isBlank(request.title())) {
            throw new IllegalArgumentException("Title is required");
        }
        if (request.dueDate() != null && request.dueDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Due date must be in the future");
        }
        if (loggerUserDoesntOwnThis(todolist)) {
            throw new IllegalArgumentException("User does not own this todolist");
        }
    }

    private static boolean loggerUserDoesntOwnThis(Todolist todolist) {
        User userAuthenticated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return !todolist.user().getId().equals(userAuthenticated.getId());
    }
}
