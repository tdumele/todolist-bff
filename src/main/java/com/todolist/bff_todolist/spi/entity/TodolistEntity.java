package com.todolist.bff_todolist.spi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @Column(name = "TODOLIST_CHECKED")
    private Boolean checked;

    @ManyToOne
    @JoinColumn(name = "TODOLIST_CATEGORY_ID")
    private CategoryEntity category;

    @Column(name = "TODOLIST_DUE_DATE")
    private LocalDateTime dueDate;
}
