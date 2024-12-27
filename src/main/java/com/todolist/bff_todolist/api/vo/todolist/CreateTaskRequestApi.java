package com.todolist.bff_todolist.api.vo.todolist;

import java.time.LocalDateTime;

public record CreateTaskRequestApi(String title,
                                   String description,
                                   LocalDateTime dueDate) {
}
