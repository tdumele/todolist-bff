package com.todolist.bff_todolist.spi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TASK")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "TASK_ID")
    private UUID id;

    @Column(name = "TASK_TITLE")
    private String title;

    @Column(name = "TASK_DESCRIPTION")
    private String description;

    @Column(name = "TASK_CHECKED")
    private Boolean checked;

    @Column(name = "TASK_DUE_DATE")
    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TODOLIST_ID")
    private TodolistEntity todolist;
}
