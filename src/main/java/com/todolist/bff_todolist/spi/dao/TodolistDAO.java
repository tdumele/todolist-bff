package com.todolist.bff_todolist.spi.dao;

import com.todolist.bff_todolist.spi.entity.TodolistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TodolistDAO extends JpaRepository<TodolistEntity, UUID> {
}
