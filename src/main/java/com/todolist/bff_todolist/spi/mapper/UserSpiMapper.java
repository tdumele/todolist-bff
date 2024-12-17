package com.todolist.bff_todolist.spi.mapper;

import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.spi.entity.user.UserAccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSpiMapper {

    User mapTo(UserAccountEntity entity);
}
