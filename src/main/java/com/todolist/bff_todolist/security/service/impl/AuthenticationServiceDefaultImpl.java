package com.todolist.bff_todolist.security.service.impl;

import com.todolist.bff_todolist.domain.model.user.Role;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.model.user.bo.CreateUser;
import com.todolist.bff_todolist.security.controller.dto.LoginRequest;
import com.todolist.bff_todolist.security.controller.dto.SignupRequest;
import com.todolist.bff_todolist.security.service.AuthenticationService;
import com.todolist.bff_todolist.spi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceDefaultImpl implements AuthenticationService {

    public static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public User signup(SignupRequest signupRequest) {
        checkArgument(signupRequest);

        User user = new User();
        user.setUsername(signupRequest.username());
        user.setPassword(passwordEncoder.encode(signupRequest.password()));
        user.setEmail(signupRequest.email());
        user.setRoles(Set.of(Role.USER));
        user.setAccountDueTime(Instant.now().plus(360, ChronoUnit.DAYS));
        user.setCredentialsDueTime(Instant.now().plus(360, ChronoUnit.DAYS));
        user.setEnabled(true);
        user.setAccountNonLocked(true);

        return userRepository.createUser(user);
    }

    @Override
    public User authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        return userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private void checkArgument(SignupRequest signupRequest) {
        if (signupRequest.username().isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (signupRequest.password().isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }
        if (signupRequest.email().isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (signupRequest.email().length() < 5) {
            throw new IllegalArgumentException("Email is too short");
        }
        if (!emailPattern.matcher(signupRequest.email()).matches()) {
            throw new IllegalArgumentException("Email is not valid");
        }
    }
}
