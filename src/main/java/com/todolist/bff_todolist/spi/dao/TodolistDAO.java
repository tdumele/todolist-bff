package com.todolist.bff_todolist.spi.dao;

import com.todolist.bff_todolist.spi.entity.TaskEntity;
import com.todolist.bff_todolist.spi.entity.TodolistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodolistDAO extends JpaRepository<TodolistEntity, UUID> {
    List<TodolistEntity> findAllByUser_Id(UUID userId);
}
