package com.todolist.bff_todolist.security.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignupRequest(@NotNull String username, @NotNull String password, @NotNull @Email String email) {
}
