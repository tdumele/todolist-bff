package com.todolist.bff_todolist.spi.mapper;

import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.spi.entity.TodolistEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodolistSpiMapper {

    Todolist mapTo(TodolistEntity entity);
}
