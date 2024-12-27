package com.todolist.bff_todolist.domain.model;

import java.util.UUID;

public record Todolist (
    UUID id,
    String title,
    String description,
    UUID userId) {
}
