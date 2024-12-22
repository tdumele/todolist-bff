package com.todolist.bff_todolist.security.controller.dto;

public record LoginResponse (String token, long expiration) {
}
