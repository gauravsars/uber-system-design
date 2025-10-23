package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.dto.CreateUserRequest;
import com.dehradun.cabbooking.entity.User;
import com.dehradun.cabbooking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing user management endpoints.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Builds the controller with the service dependency.
     *
     * @param userService business service managing users
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user profile in the system.
     *
     * @param request user creation payload
     * @return response entity wrapping the created user
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User created = userService.createUser(request);
        return ResponseEntity.status(201).body(created);
    }

    /**
     * Fetches a user profile by identifier.
     *
     * @param userId identifier of the user to load
     * @return user entity
     */
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}
