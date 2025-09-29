package com.example.ToDoApp.repository;

import com.example.ToDoApp.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {}
