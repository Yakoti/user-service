package com.ridetogether.user_service.controller;

import com.ridetogether.user_service.dto.UserDto;
import com.ridetogether.user_service.model.User;
import com.ridetogether.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable long id) throws InterruptedException {
        Optional<UserDto> user = userService.findById(id);
        if (user.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.get());
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {
        System.out.println(user);
        return  ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return ResponseEntity.noContent().build();
    }
}