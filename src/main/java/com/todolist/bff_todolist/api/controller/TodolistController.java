package com.todolist.bff_todolist.api.controller;

import com.todolist.bff_todolist.api.mapper.TaskMapper;
import com.todolist.bff_todolist.api.vo.todolist.CreateTaskRequestApi;
import com.todolist.bff_todolist.api.vo.todolist.GetTodolistResponse;
import com.todolist.bff_todolist.api.vo.todolist.GetTodolistTaskResponse;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.domain.service.TodolistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todolists")
@Tag(name = "Todolist", description = "API for managing todolists")
public class TodolistController {

    private final TodolistService todolistService;
    private final TaskMapper mapper;

    @GetMapping
    @Operation(summary = "Get all todolists", description = "Retrieve a list of all todolists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTodolistResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<GetTodolistResponse>> getAllTodolists(Authentication authentication) {
        var user = (User) authentication.getPrincipal();
        var todolists = todolistService.getAllTodolistsByUser(user);
        var todolistsMapped = todolists.stream().map(mapper::mapTo).toList();
        return ResponseEntity.ok(todolistsMapped);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get todolist by ID", description = "Retrieve a specific todolist by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved todolist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTodolistResponse.class))),
            @ApiResponse(responseCode = "404", description = "Todolist not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GetTodolistResponse> getTodolistById(@PathVariable("id") UUID id) {
        var todolist = todolistService.getTodolistById(id);
        var todolistMapped = todolist.map(mapper::mapTo);
        return ResponseEntity.of(todolistMapped);
    }

    @GetMapping("/{id}/tasks")
    @Operation(summary = "Get tasks from todolist", description = "Retrieve a list of tasks from a specific todolist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of tasks",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTodolistTaskResponse.class))),
            @ApiResponse(responseCode = "404", description = "Todolist not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<GetTodolistTaskResponse>> getTasksFromTodolist(@PathVariable("id") UUID id) {
        var tasks = todolistService.getTasksFromTodolist(id);
        var tasksMapped = tasks.stream().map(mapper::mapTo).toList();
        return ResponseEntity.ok(tasksMapped);
    }

    @PostMapping("/{id}/tasks")
    @Operation(summary = "Create a task in a todolist", description = "Create a task in a specific todolist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetTodolistTaskResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<GetTodolistTaskResponse> createTaskInTodolist(@PathVariable("id") UUID id, @RequestBody CreateTaskRequestApi request) {
        var task = todolistService.createTaskInTodolist(id, mapper.mapTo(request));
        return ResponseEntity.created(URI.create("/api/v1/todolists/" + id + "/tasks/" + task.id())).build();
    }
}
