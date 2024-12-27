package com.todolist.bff_todolist.domain.model;

import java.time.LocalDateTime;

public record CreateTaskRequest(String title,
                                String description,
                                LocalDateTime dueDate) {
}
