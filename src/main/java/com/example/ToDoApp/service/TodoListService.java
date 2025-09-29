package com.example.ToDoApp.service;

import com.example.ToDoApp.entity.AppUser;
import com.example.ToDoApp.entity.TodoList;
import com.example.ToDoApp.repository.TodoListRepository;
import com.example.ToDoApp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepo;
    private final UserRepository userRepo;

    public TodoListService(TodoListRepository todoListRepo, UserRepository userRepo) {
        this.todoListRepo = todoListRepo;
        this.userRepo = userRepo;
    }

    public List<TodoList> getUserTodoLists(int userId) {
        AppUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getTodoLists();
    }

    public TodoList createTodoList(int userId, TodoList list) {
        AppUser user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        list.setUser(user);
        return todoListRepo.save(list);
    }
}
