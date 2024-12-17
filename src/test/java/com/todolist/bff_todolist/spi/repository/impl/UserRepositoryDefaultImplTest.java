package com.todolist.bff_todolist.spi.repository.impl;

import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.spi.mapper.UserSpiMapperImpl;
import com.todolist.bff_todolist.spi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Sql("/data.sql")
@Import({
        UserRepositoryDefaultImpl.class, UserSpiMapperImpl.class
})
class UserRepositoryDefaultImplTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void getUserByIdReturnsUserWhenIdExists() {
        UUID id = UUID.fromString("14e310d3-a741-4751-ab54-f378a72e9871");

        Optional<User> actualUser = userRepository.getUserById(id);

        assertTrue(actualUser.isPresent());
    }

    @Test
    void getUserByIdReturnsEmptyWhenIdDoesNotExist() {
        UUID id = UUID.randomUUID();

        Optional<User> actualUser = userRepository.getUserById(id);

        assertTrue(actualUser.isEmpty());
    }
}