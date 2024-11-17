package com.todolist.bff_todolist.api.vo;

import java.util.UUID;

public record GetTodolistResponse(UUID id,
                                   String title,
                                   String description,
                                   String status
){}