package com.todolist.bff_todolist.spi.repository.impl;

import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.spi.dao.UserDAO;
import com.todolist.bff_todolist.spi.mapper.UserSpiMapper;
import com.todolist.bff_todolist.spi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryDefaultImpl implements UserRepository {

    private final UserDAO userDAO;
    private final UserSpiMapper userMapper;

    @Override
    public Optional<User> getUserById(UUID uuid) {
        var entity = userDAO.findById(uuid);
        return entity.map(userMapper::mapTo);
    }

    @Override
    public User createUser(User user) {
        var entity = userMapper.mapTo(user);
        entity.setId(UUID.randomUUID());
        userDAO.save(entity);
        return userMapper.mapTo(entity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDAO.findByUsername(username).map(userMapper::mapTo);
    }
}
