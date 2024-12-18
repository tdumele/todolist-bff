package com.todolist.bff_todolist.api.mapper;

import com.todolist.bff_todolist.api.vo.user.CreateUserRequest;
import com.todolist.bff_todolist.api.vo.user.GetUserResponse;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.model.user.bo.CreateUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    GetUserResponse mapTo(User user);
    CreateUser mapTo(CreateUserRequest createUser);
}
