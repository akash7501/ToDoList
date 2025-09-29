package com.example.ToDoApp.service;

import com.example.ToDoApp.entity.AppUser;
import com.example.ToDoApp.entity.Task;
import com.example.ToDoApp.entity.TodoList;
import com.example.ToDoApp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<AppUser> getAllUsers() {
        return repo.findAll();
    }

    public AppUser getUserById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public AppUser createUser(AppUser user) {
        if (user.getTodoLists() != null) {
            for (TodoList list : user.getTodoLists()) {
                list.setUser(user);  // Set parent reference for each list
                if (list.getTasks() != null) {
                    for (Task task : list.getTasks()) {
                        task.setTodoList(list);  // Set parent reference for each task
                    }
                }
            }
        }
        return repo.save(user);
    }




    public AppUser updateUser(int id, AppUser updatedUser) {
        AppUser existing = getUserById(id);
        existing.setUsername(updatedUser.getUsername());
        existing.setEmail(updatedUser.getEmail());
        existing.setPassword(updatedUser.getPassword());
        return repo.save(existing);
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }
}
