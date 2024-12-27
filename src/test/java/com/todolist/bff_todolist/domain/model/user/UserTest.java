package com.todolist.bff_todolist.domain.model.user;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
class UserTest {

    @Test
    void isAccountNonExpiredReturnsTrueWhenAccountIsNotExpired() {
        User user = new User();
        user.setAccountDueTime(Instant.now().plusSeconds(60));

        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonExpiredReturnsFalseWhenAccountIsExpired() {
        User user = new User();
        user.setAccountDueTime(Instant.now().minusSeconds(60));

        assertFalse(user.isAccountNonExpired());
    }

    @Test
    void isAccountNonLockedReturnsTrueWhenAccountIsNotLocked() {
        User user = new User();
        user.setAccountNonLocked(true);

        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void isAccountNonLockedReturnsFalseWhenAccountIsLocked() {
        User user = new User();
        user.setAccountNonLocked(false);

        assertFalse(user.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpiredReturnsTrueWhenCredentialsAreNotExpired() {
        User user = new User();
        user.setCredentialsDueTime(Instant.now().plusSeconds(60));

        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void isCredentialsNonExpiredReturnsFalseWhenCredentialsAreExpired() {
        User user = new User();
        user.setCredentialsDueTime(Instant.now().minusSeconds(60));

        assertFalse(user.isCredentialsNonExpired());
    }

    @Test
    void isEnabledReturnsTrueWhenUserIsEnabled() {
        User user = new User();
        user.setEnabled(true);

        assertTrue(user.isEnabled());
    }

    @Test
    void isEnabledReturnsFalseWhenUserIsNotEnabled() {
        User user = new User();
        user.setEnabled(false);

        assertFalse(user.isEnabled());
    }

    @Test
    void getAuthoritiesReturnsGrantedAuthorities() {
        User user = new User();
        user.setRoles(Set.of(Role.USER, Role.ADMIN));

        assertEquals(2, user.getAuthorities().size());
    }

    @Test
    void getIdReturnsCorrectId() {
        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);

        assertEquals(id, user.getId());
    }

    @Test
    void getUsernameReturnsCorrectUsername() {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        assertEquals(username, user.getUsername());
    }

    @Test
    void getPasswordReturnsCorrectPassword() {
        String password = "password";
        User user = new User();
        user.setPassword(password);

        assertEquals(password, user.getPassword());
    }

    @Test
    void getEmailReturnsCorrectEmail() {
        String email = "user@example.com";
        User user = new User();
        user.setEmail(email);

        assertEquals(email, user.getEmail());
    }
}