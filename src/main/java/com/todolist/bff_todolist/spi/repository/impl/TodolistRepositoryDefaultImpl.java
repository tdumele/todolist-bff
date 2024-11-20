package com.todolist.bff_todolist.spi.repository.impl;

import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.spi.dao.TodolistDAO;
import com.todolist.bff_todolist.spi.mapper.TodolistSpiMapper;
import com.todolist.bff_todolist.spi.repository.TodolistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TodolistRepositoryDefaultImpl implements TodolistRepository {

    private final TodolistDAO todolistDAO;
    private final TodolistSpiMapper mapper;

    @Override
    public List<Todolist> getAllTodolists() {
        var entities = todolistDAO.findAll();
        return entities.stream().map(mapper::mapTo).toList();
    }

    @Override
    public Optional<Todolist> getTodolistById(UUID id) {
        var entity = todolistDAO.findById(id);
        return entity.map(mapper::mapTo);
    }
}
