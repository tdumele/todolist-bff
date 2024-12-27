package com.todolist.bff_todolist.domain.service.impl;

import com.todolist.bff_todolist.domain.model.CreateTaskRequest;
import com.todolist.bff_todolist.domain.model.Task;
import com.todolist.bff_todolist.domain.model.Todolist;
import com.todolist.bff_todolist.domain.model.user.User;
import com.todolist.bff_todolist.spi.repository.TodolistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodolistServiceDefaultImplTest {

    @Mock
    private TodolistRepository todolistRepository;

    @InjectMocks
    private TodolistServiceDefaultImpl todolistService;

    @Test
    void getAllTodolistsReturnsListOfTodolists() {
        List<Todolist> expectedTodolists = List.of(new Todolist(UUID.randomUUID(), "Title", "Description", UUID.randomUUID())
                , new Todolist(UUID.randomUUID(), "Title", "Description", UUID.randomUUID()));
        when(todolistRepository.getAllTodolists()).thenReturn(expectedTodolists);

        List<Todolist> actualTodolists = todolistService.getAllTodolists();

        assertEquals(expectedTodolists, actualTodolists);
    }

    @Test
    void getTodolistByIdReturnsTodolistWhenIdExists() {
        UUID id = UUID.randomUUID();
        Todolist expectedTodolist = new Todolist(id, "Title", "Description", UUID.randomUUID());
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.of(expectedTodolist));

        Optional<Todolist> actualTodolist = todolistService.getTodolistById(id);

        assertTrue(actualTodolist.isPresent());
        assertEquals(expectedTodolist, actualTodolist.get());
    }

    @Test
    void getTodolistByIdReturnsEmptyWhenIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.empty());

        Optional<Todolist> actualTodolist = todolistService.getTodolistById(id);

        assertTrue(actualTodolist.isEmpty());
    }

    @Test
    void getTasksFromTodolistReturnsListOfTasksWhenIdExists() {
        UUID id = UUID.randomUUID();
        var todolist = new Todolist(id, "Title", "Description", UUID.randomUUID());
        List<Task> expectedTasks = List.of(new Task(UUID.randomUUID(), "Title1", "Description1", false, LocalDateTime.now().plusDays(1), todolist),
                new Task(UUID.randomUUID(), "Title2", "Description2", false, LocalDateTime.now().plusDays(2), todolist));
        when(todolistRepository.getTasksFromTodolist(id)).thenReturn(expectedTasks);

        List<Task> actualTasks = todolistService.getTasksFromTodolist(id);

        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void getTasksFromTodolistReturnsEmptyListWhenIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(todolistRepository.getTasksFromTodolist(id)).thenReturn(List.of());

        List<Task> actualTasks = todolistService.getTasksFromTodolist(id);

        assertTrue(actualTasks.isEmpty());
    }

    @Test
    void getAllTodolistsByUserReturnsListOfTodolistsWhenUserExists() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        List<Todolist> expectedTodolists = List.of(new Todolist(UUID.randomUUID(), "Title1", "Description1", userId),
                new Todolist(UUID.randomUUID(), "Title2", "Description2", userId));
        when(todolistRepository.getAllTodolistsByUserId(userId)).thenReturn(expectedTodolists);

        List<Todolist> actualTodolists = todolistService.getAllTodolistsByUser(user);

        assertEquals(expectedTodolists, actualTodolists);
    }

    @Test
    void getAllTodolistsByUserReturnsEmptyListWhenUserHasNoTodolists() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        when(todolistRepository.getAllTodolistsByUserId(userId)).thenReturn(List.of());

        List<Todolist> actualTodolists = todolistService.getAllTodolistsByUser(user);

        assertTrue(actualTodolists.isEmpty());
    }


    @Test
    void createTaskInTodolistCreatesTaskWhenRequestIsValid() {
        UUID id = UUID.randomUUID();
        CreateTaskRequest request = new CreateTaskRequest("Title", "Description", LocalDateTime.now().plusDays(1));
        UUID userId = UUID.randomUUID();

        User user = new User();
        user.setId(userId);

        Todolist todolist = new Todolist(id, "Title", "Description", userId);
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.of(todolist));
        when(todolistRepository.createTask(any(Task.class))).thenReturn(new Task(
                UUID.randomUUID(), "Title", "Description", false, LocalDateTime.now().plusDays(1), todolist));

        try (MockedStatic<SecurityContextHolder> mockedStatic = mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);
            when(SecurityContextHolder.getContext()).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(user);

            Task task = todolistService.createTaskInTodolist(id, request);

            assertNotNull(task);
        }
    }

    @Test
    void createTaskInTodolistThrowsExceptionWhenTodolistNotFound() {
        UUID id = UUID.randomUUID();
        CreateTaskRequest request = new CreateTaskRequest("Title", "Description", LocalDateTime.now().plusDays(1));
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> todolistService.createTaskInTodolist(id, request));
    }

    @Test
    void createTaskInTodolistThrowsExceptionWhenUserIsNotOwner() {
        UUID id = UUID.randomUUID();
        CreateTaskRequest request = new CreateTaskRequest("Title", "Description", LocalDateTime.now().plusDays(1));
        Todolist todolist = new Todolist(id, "Title", "Description", UUID.randomUUID());
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.of(todolist));
        try (MockedStatic<SecurityContextHolder> mockedStatic = mockStatic(SecurityContextHolder.class)) {
            SecurityContext securityContext = mock(SecurityContext.class);
            Authentication authentication = mock(Authentication.class);
            when(SecurityContextHolder.getContext()).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(new User());

            assertThrows(IllegalArgumentException.class, () -> todolistService.createTaskInTodolist(id, request));
        }
    }

    @Test
    void createTaskInTodolistThrowsExceptionWhenTitleIsBlank() {
        UUID id = UUID.randomUUID();
        CreateTaskRequest request = new CreateTaskRequest("", "Description", LocalDateTime.now().plusDays(1));
        Todolist todolist = new Todolist(id, "Title", "Description", UUID.randomUUID());
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.of(todolist));

        assertThrows(IllegalArgumentException.class, () -> todolistService.createTaskInTodolist(id, request));
    }

    @Test
    void createTaskInTodolistThrowsExceptionWhenDescriptionIsBlank() {
        UUID id = UUID.randomUUID();
        CreateTaskRequest request = new CreateTaskRequest("Title", "", LocalDateTime.now().plusDays(1));
        Todolist todolist = new Todolist(id, "Title", "Description", UUID.randomUUID());
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.of(todolist));

        assertThrows(IllegalArgumentException.class, () -> todolistService.createTaskInTodolist(id, request));
    }

    @Test
    void createTaskInTodolistThrowsExceptionWhenDueDateIsInThePast() {
        UUID id = UUID.randomUUID();
        CreateTaskRequest request = new CreateTaskRequest("Title", "Description", LocalDateTime.now().minusDays(1));
        Todolist todolist = new Todolist(id, "Title", "Description", UUID.randomUUID());
        when(todolistRepository.getTodolistById(id)).thenReturn(Optional.of(todolist));

        assertThrows(IllegalArgumentException.class, () -> todolistService.createTaskInTodolist(id, request));
    }
}