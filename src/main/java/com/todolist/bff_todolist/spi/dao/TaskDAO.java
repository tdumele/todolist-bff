package com.todolist.bff_todolist.spi.dao;

import com.todolist.bff_todolist.spi.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskDAO extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findAllByTodolist_Id(UUID todolistId);
}