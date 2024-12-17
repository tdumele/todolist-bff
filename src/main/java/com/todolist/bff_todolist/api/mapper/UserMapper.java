package com.todolist.bff_todolist.api.mapper;

import com.todolist.bff_todolist.api.vo.user.GetUserResponse;
import com.todolist.bff_todolist.domain.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    GetUserResponse mapTo(User user);
}
