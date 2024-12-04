package com.todolist.bff_todolist.api.vo;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetTodolistTaskResponse(UUID id,
                                      String title,
                                      String description,
                                      Boolean checked,
                                      LocalDateTime dueDate) {
}
