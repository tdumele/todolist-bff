package com.todolist.bff_todolist.api.mapper;

import com.todolist.bff_todolist.api.vo.todolist.GetTodolistResponse;
import com.todolist.bff_todolist.api.vo.todolist.GetTodolistTaskResponse;
import com.todolist.bff_todolist.domain.model.Task;
import com.todolist.bff_todolist.domain.model.Todolist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    GetTodolistResponse mapTo(Todolist todolist);
    GetTodolistTaskResponse mapTo(Task task);
}
