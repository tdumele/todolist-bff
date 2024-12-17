package com.todolist.bff_todolist.api.vo.user;

import com.todolist.bff_todolist.domain.model.user.Role;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record GetUserResponse(UUID id, String username,
                              Instant accountDueTime, boolean accountNonLocked, Instant credentialsDueTime,
                              boolean enabled, List<Role> roles, String email) {
}
