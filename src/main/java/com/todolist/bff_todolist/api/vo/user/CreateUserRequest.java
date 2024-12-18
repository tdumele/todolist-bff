package com.todolist.bff_todolist.api.vo.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CreateUserRequest(@NotNull String username, @NotNull String password, @NotNull @Email String email) {
}
