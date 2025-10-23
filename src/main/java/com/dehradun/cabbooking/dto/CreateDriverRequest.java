package com.dehradun.cabbooking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request payload to register a professional driver partner.
 */
public class CreateDriverRequest {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 15)
    private String phone;

    @Email
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String licenseNumber;

    /**
     * Provides the legal name of the driver partner.
     *
     * @return driver name string
     */
    public String getName() {
        return name;
    }

    /**
     * Registers the legal name of the driver partner.
     *
     * @param name driver name string
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the driver contact number.
     *
     * @return contact phone string
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the driver contact number.
     *
     * @param phone contact phone string
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Provides the optional driver email address.
     *
     * @return driver email value
     */
    public String getEmail() {
        return email;
    }

    /**
     * Configures the optional driver email address.
     *
     * @param email driver email value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the transport authority license number.
     *
     * @return government issued license string
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets the transport authority license number.
     *
     * @param licenseNumber government issued license string
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
}
