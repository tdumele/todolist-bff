package com.todolist.bff_todolist.api.controller;

import com.todolist.bff_todolist.api.mapper.TodolistMapper;
import com.todolist.bff_todolist.api.vo.GetTodolistResponse;
import com.todolist.bff_todolist.domain.service.TodolistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todolists")
public class TodolistController {

    private final TodolistService todolistService;
    private final TodolistMapper mapper;

    // Careful this endpoint can retrieve a lot of data, hide it in production
    @GetMapping
    public ResponseEntity<List<GetTodolistResponse>> getAllTodolists() {
        var todolists = todolistService.getAllTodolists();
        var todolistsMapped = todolists.stream().map(mapper::mapTo).toList();
        return ResponseEntity.ok(todolistsMapped);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTodolistResponse> getTodolistById(@PathVariable("id") UUID id) {
        var todolist = todolistService.getTodolistById(id);
        var todolistMapped = todolist.map(mapper::mapTo);
        return ResponseEntity.of(todolistMapped);
    }
}
