package com.todolist.bff_todolist.security.controller;

import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.security.JwtService;
import com.todolist.bff_todolist.security.controller.dto.LoginRequest;
import com.todolist.bff_todolist.security.controller.dto.LoginResponse;
import com.todolist.bff_todolist.security.controller.dto.SignupRequest;
import com.todolist.bff_todolist.security.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping
    public ResponseEntity<Void> signup(@RequestBody @Valid SignupRequest signupRequest) {
        var optionalGetUserResponse = authenticationService.signup(signupRequest);
        return ResponseEntity.created(URI.create("" + optionalGetUserResponse.getId())).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        User user = authenticationService.authenticate(loginRequest);
        var loginResponse = new LoginResponse(jwtService.generateToken(user), jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }



}
