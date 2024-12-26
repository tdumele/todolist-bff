package com.todolist.bff_todolist.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Todolist {
    private UUID id;
    private String title;
    private String description;
    private UUID userId;
}
