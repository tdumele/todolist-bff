package com.todolist.bff_todolist.spi.dao;

import com.todolist.bff_todolist.spi.entity.user.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAO extends JpaRepository<UserAccountEntity, UUID> {

    Optional<UserAccountEntity> findByUsername(String username);
}
