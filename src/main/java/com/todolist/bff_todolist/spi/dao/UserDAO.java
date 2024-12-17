package com.todolist.bff_todolist.spi.dao;

import com.todolist.bff_todolist.spi.entity.user.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserDAO extends JpaRepository<UserAccountEntity, UUID> {
}
