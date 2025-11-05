package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.dto.UserCreateRequest;
import com.dehradun.cabbooking.entity.User;
import com.dehradun.cabbooking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes REST endpoints for user registration and lookup.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user record.
     *
     * @param request payload describing the new user
     * @return response containing the created user
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequest request) {
        User saved = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Fetches a user by identifier.
     *
     * @param userId identifier of the desired user
     * @return matching user entity
     */
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
}
