package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.dto.CreateUserRequest;
import com.dehradun.cabbooking.entity.User;
import com.dehradun.cabbooking.enums.UserStatus;
import com.dehradun.cabbooking.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Business service providing user management operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Builds the service with the repository dependency.
     *
     * @param userRepository data access component for users
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user profile using the supplied payload.
     *
     * @param request request payload describing the user to create
     * @return persisted user entity
     */
    @Transactional
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPasswordHash(request.getPasswordHash());
        user.setStatus(UserStatus.ACTIVE);
        user.setDeleted(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(OffsetDateTime.now());
        return userRepository.save(user);
    }

    /**
     * Loads a user by identifier, ensuring the profile is active.
     *
     * @param userId identifier of the desired user
     * @return user entity from the database
     */
    public User getUserById(Long userId) {
        return userRepository
            .findById(userId)
            .filter(user -> !user.isDeleted())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
