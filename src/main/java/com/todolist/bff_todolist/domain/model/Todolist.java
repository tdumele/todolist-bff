package com.todolist.bff_todolist.domain.model;

import com.todolist.bff_todolist.domain.model.user.User;

import java.util.UUID;

public record Todolist (
    UUID id,
    String title,
    String description,
    User user) {
}
