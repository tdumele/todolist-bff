package com.todolist.bff_todolist.domain.service.impl;

import com.todolist.bff_todolist.api.vo.todolist.CreateTaskRequest;
import com.todolist.bff_todolist.domain.model.Task;
import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.service.TodolistService;
import com.todolist.bff_todolist.spi.repository.TodolistRepository;
import lombok.RequiredArgsConstructor;
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
    public Task createTaskInTodolist(UUID id, CreateTaskRequest request) {
        Todolist todolist = todolistRepository.getTodolistById(id).orElseThrow(() -> new IllegalStateException("Todolist not found"));
        checkTaskRequest(todolist, request);

        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        task.setTodolist(todolist);
        
        return todolistRepository.createTask(task);
    }

    private void checkTaskRequest(Todolist todolist, CreateTaskRequest request) {
        if ( !loggerUserOwnThis(todolist)) {
            throw new IllegalArgumentException("User is not the owner of the todolist");
        }

        if (request.title() == null || request.title().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (request.description() == null || request.description().isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (request.dueDate() != null && request.dueDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Due date must be in the future");
        }
    }

    private static boolean loggerUserOwnThis(Todolist todolist) {
        User userAuthenticated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return todolist.getUserId().equals(userAuthenticated.getId());
    }
}
