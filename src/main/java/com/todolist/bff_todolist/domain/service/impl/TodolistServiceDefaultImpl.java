package com.todolist.bff_todolist.domain.service.impl;

import com.todolist.bff_todolist.domain.model.Task;
import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.service.TodolistService;
import com.todolist.bff_todolist.spi.repository.TodolistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
