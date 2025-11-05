package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

/**
 * Maps the users table storing rider profiles for the platform.
 */
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 15, unique = true)
    private String phone;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 200)
    private String passwordHash;

    @Column(name = "status", nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    /**
     * Retrieves the generated user identifier.
     *
     * @return unique identifier for the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Updates the user identifier, typically used by JPA.
     *
     * @param id identifier assigned by the database
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Provides the human readable name of the user.
     *
     * @return rider name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the descriptive name for the user.
     *
     * @param name rider name to persist
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Supplies the registered phone number.
     *
     * @return contact phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Adjusts the registered phone number.
     *
     * @param phone contact number to save
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Obtains the optional email address.
     *
     * @return rider email if available
     */
    public String getEmail() {
        return email;
    }

    /**
     * Assigns an email address to the user profile.
     *
     * @param email contact email to store
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the hashed password for authentication.
     *
     * @return password hash string
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Updates the stored password hash value.
     *
     * @param passwordHash securely hashed password
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Exposes the current lifecycle state of the user.
     *
     * @return user status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Records the lifecycle state of the user account.
     *
     * @param status new user status
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Indicates whether the user has been soft deleted.
     *
     * @return true if the record is marked deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Toggles the soft delete flag for the user.
     *
     * @param deleted flag value to persist
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Provides the creation timestamp assigned by the database.
     *
     * @return record creation time
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp, primarily used for testing.
     *
     * @param createdAt timestamp to assign
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Reveals the last update timestamp if available.
     *
     * @return last modification time
     */
    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Captures the latest update timestamp from application logic.
     *
     * @param updatedAt modification timestamp
     */
    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
