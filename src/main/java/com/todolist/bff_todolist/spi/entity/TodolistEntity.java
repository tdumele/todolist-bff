package com.todolist.bff_todolist.spi.entity;

import com.todolist.bff_todolist.spi.entity.user.UserAccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TODOLIST")
public class TodolistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "TODOLIST_ID")
    private UUID id;

    @Column(name = "TODOLIST_TITLE")
    private String title;

    @Column(name = "TODOLIST_DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserAccountEntity user;

}
