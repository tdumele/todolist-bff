package com.todolist.bff_todolist.api.vo.todolist;

import java.time.LocalDateTime;
import java.util.UUID;

public record PutTodolistTaskRequest(UUID id,
                                     String title,
                                     String description,
                                     Boolean checked,
                                     LocalDateTime dueDate) {
}
