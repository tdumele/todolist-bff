package com.todolist.bff_todolist.spi.repository.impl;

import com.todolist.bff_todolist.domain.model.Task;
import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.spi.dao.TaskDAO;
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

    private final TaskDAO taskDAO;
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

    @Override
    public List<Task> getTasksFromTodolist(UUID id) {
        var entities = taskDAO.findAllByTodolist_Id(id);
        return entities.stream().map(mapper::mapTo).toList();
    }

    @Override
    public List<Todolist> getAllTodolistsByUserId(UUID id) {
        var entities = todolistDAO.findAllByUser_Id(id);
        return entities.stream().map(mapper::mapTo).toList();
    }

    @Override
    public Task createTask(Task task) {
        var entity = mapper.mapTo(task);
        var savedEntity = taskDAO.save(entity);
        return mapper.mapTo(savedEntity);
    }


}
