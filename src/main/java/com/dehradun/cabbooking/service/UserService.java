package com.dehradun.cabbooking.service;

import com.dehradun.cabbooking.dto.UserCreateRequest;
import com.dehradun.cabbooking.entity.User;
import com.dehradun.cabbooking.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 * Handles business logic for user onboarding and retrieval.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user using the provided payload.
     *
     * @param request data required to create the user
     * @return the persisted user entity
     */
    @Transactional
    public User createUser(UserCreateRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPasswordHash(hashPassword(request.getPassword()));
        user.setCreatedAt(OffsetDateTime.now());
        return userRepository.save(user);
    }

    /**
     * Retrieves an active user by identifier.
     *
     * @param userId identifier of the user to load
     * @return matching user entity
     */
    @Transactional(readOnly = true)
    public User getUserById(Long userId) {
        return userRepository
            .findByIdAndDeletedFalse(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    /**
     * Hashes a plaintext password using SHA-256 for storage.
     *
     * @param password plaintext password to hash
     * @return hexadecimal representation of the hashed password
     */
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte b : hash) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to hash password", ex);
        }
    }
}
