package com.todolist.bff_todolist.api.mapper;

import com.todolist.bff_todolist.api.vo.GetTodolistCategoryResponse;
import com.todolist.bff_todolist.api.vo.GetTodolistResponse;
import com.todolist.bff_todolist.domain.model.Category;
import com.todolist.bff_todolist.domain.model.Todolist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TodolistMapper {

    GetTodolistResponse mapTo(Todolist todolist);

    GetTodolistCategoryResponse mapTo(Category category);
}
