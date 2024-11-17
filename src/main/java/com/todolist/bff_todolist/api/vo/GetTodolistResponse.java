package com.todolist.bff_todolist.api.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class GetTodolistResponse {

    private UUID id;
    private String title;
    private String description;
    private String status;
}
