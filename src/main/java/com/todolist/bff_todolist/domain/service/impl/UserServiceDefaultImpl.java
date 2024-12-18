package com.todolist.bff_todolist.domain.service.impl;

import com.todolist.bff_todolist.domain.model.user.Role;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.model.user.bo.CreateUser;
import com.todolist.bff_todolist.domain.service.UserService;
import com.todolist.bff_todolist.spi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserServiceDefaultImpl implements UserService {

    public static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserById(UUID uuid) {
        return userRepository.getUserById(uuid);
    }

    @Override
    public User createUser(CreateUser createUser) {
        checkArgument(createUser);

        User user = new User();
        user.setUsername(createUser.username());
        user.setPassword(passwordEncoder.encode(createUser.password()));
        user.setEmail(createUser.email());
        user.setRoles(Set.of(Role.USER));
        user.setAccountDueTime(Instant.now().plus(360, ChronoUnit.DAYS));
        user.setCredentialsDueTime(Instant.now().plus(360, ChronoUnit.DAYS));
        user.setEnabled(true);
        user.setAccountNonLocked(true);

        return userRepository.createUser(user);
    }

    private void checkArgument(CreateUser createUser) {
        if (createUser.username().isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (createUser.password().isBlank()) {
            throw new IllegalArgumentException("Password cannot be blank");
        }
        if (createUser.email().isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (createUser.email().length() < 5) {
            throw new IllegalArgumentException("Email is too short");
        }
        if (!emailPattern.matcher(createUser.email()).matches()) {
            throw new IllegalArgumentException("Email is not valid");
        }
    }
}
