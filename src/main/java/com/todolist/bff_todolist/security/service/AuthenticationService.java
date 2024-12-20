package com.todolist.bff_todolist.security.service;

import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.security.controller.dto.LoginRequest;
import com.todolist.bff_todolist.security.controller.dto.SignupRequest;

public interface AuthenticationService {

    User signup(SignupRequest signupRequest);
    User authenticate(LoginRequest loginRequest);
}
