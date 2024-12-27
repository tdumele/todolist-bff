package com.todolist.bff_todolist.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;


public record Task(UUID id,
                   String title,
                   String description,
                   boolean checked,
                   LocalDateTime dueDate,
                   Todolist todolist) {
}
