package com.todolist.bff_todolist.security.controller.dto;

import lombok.Getter;
import lombok.Setter;

public record LoginRequest(String username, String password) {
}
