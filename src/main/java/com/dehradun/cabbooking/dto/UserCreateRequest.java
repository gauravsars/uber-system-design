package com.dehradun.cabbooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Payload describing the information required to register a user.
 */
public class UserCreateRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Phone must contain 10-15 digits")
    private String phone;

    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 8, max = 200)
    private String password;

    /**
     * Provides the user's full name.
     *
     * @return user name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     *
     * @param name name to store
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the user's phone number string.
     *
     * @return phone digits
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Assigns the phone number for the user.
     *
     * @param phone phone digits to store
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the optional email address.
     *
     * @return email string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the optional email address.
     *
     * @param email email string to store
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Provides the raw password before hashing.
     *
     * @return plaintext password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the plaintext password; hashing occurs within the service.
     *
     * @param password plaintext password value
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
