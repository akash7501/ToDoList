package com.example.ToDoApp.controller;

import com.example.ToDoApp.entity.AppUser;
import com.example.ToDoApp.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppUser> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public AppUser getUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return service.createUser(user);
    }

    @PutMapping("/{id}")
    public AppUser updateUser(@PathVariable int id, @RequestBody AppUser user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String  deleteUser(@PathVariable int id) {
        service.deleteUser(id);
        return "User deleted successfully: " + id;
    }
}
