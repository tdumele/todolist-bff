package com.todolist.bff_todolist.api.vo.todolist;

import java.util.UUID;

public record GetTodolistResponse(UUID id,
                                  String title,
                                  String description
) {
}