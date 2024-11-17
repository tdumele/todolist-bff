package com.todolist.bff_todolist.domain.service;

import com.todolist.bff_todolist.domain.model.Todolist;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodolistService {

    List<Todolist> getAllTodolists();

    Optional<Todolist> getTodolistById(UUID id);
}
