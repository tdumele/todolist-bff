package com.todolist.bff_todolist.spi.entity.user;

import com.todolist.bff_todolist.domain.model.user.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "USER_ROLES")
@Getter
@Setter
public class UserRoles {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserAccountEntity userAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private Role role;
}