package com.todolist.bff_todolist.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Task {

    private UUID id;
    private String title;
    private String description;
    private boolean checked;
    private LocalDateTime dueDate;
    private Todolist todolist;
}
