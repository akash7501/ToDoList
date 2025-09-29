package com.example.ToDoApp.controller;

import com.example.ToDoApp.entity.AppUser;
import com.example.ToDoApp.entity.Task;
import com.example.ToDoApp.entity.TodoList;
import com.example.ToDoApp.repository.TodoListRepository;
import com.example.ToDoApp.repository.UserRepository;
import com.example.ToDoApp.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/lists")
public class TodoListController {
    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    UserRepository appUserRepo;


    private final TodoListService service;

    public TodoListController(TodoListService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoList> getUserLists(@PathVariable int userId) {
        return service.getUserTodoLists(userId);
    }

    @PostMapping
    public TodoList createTodoList(@PathVariable int userId, @RequestBody TodoList todoList) {
        AppUser user = appUserRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        todoList.setUser(user);
        for (Task t : todoList.getTasks()) {
            t.setTodoList(todoList);
        }
        return todoListRepository.save(todoList);
    }

}
