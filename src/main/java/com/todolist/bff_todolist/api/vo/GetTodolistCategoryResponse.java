package com.todolist.bff_todolist.api.vo;

import java.util.UUID;

public record GetTodolistCategoryResponse(UUID id, String name) {
}
