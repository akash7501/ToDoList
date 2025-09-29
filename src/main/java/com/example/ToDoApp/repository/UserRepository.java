package com.example.ToDoApp.repository;


import com.example.ToDoApp.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {}
