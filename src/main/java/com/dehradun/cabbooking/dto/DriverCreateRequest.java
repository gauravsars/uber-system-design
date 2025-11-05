package com.dehradun.cabbooking.dto;

import com.dehradun.cabbooking.enums.VehicleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Payload capturing the details required to register a driver and vehicle.
 */
public class DriverCreateRequest {

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
    @Size(max = 50)
    private String licenseNumber;

    @NotBlank
    @Size(max = 20)
    private String vehicleNumber;

    @Size(max = 100)
    private String vehicleModel;

    @NotNull
    private VehicleType vehicleType;

    @Positive
    private Integer capacity;

    /**
     * Returns the driver's name.
     *
     * @return driver name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the driver's name.
     *
     * @param name driver name to store
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Provides the driver's phone number.
     *
     * @return phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the driver's phone number.
     *
     * @param phone phone number to store
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retrieves the driver's email address.
     *
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the driver's email address.
     *
     * @param email email address to store
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Provides the driver's license number.
     *
     * @return license number
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets the driver's license number.
     *
     * @param licenseNumber license number to store
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Returns the vehicle registration number.
     *
     * @return vehicle number
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Sets the vehicle registration number.
     *
     * @param vehicleNumber vehicle number to store
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * Provides the descriptive model of the vehicle.
     *
     * @return vehicle model description
     */
    public String getVehicleModel() {
        return vehicleModel;
    }

    /**
     * Sets the descriptive model of the vehicle.
     *
     * @param vehicleModel model description to store
     */
    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    /**
     * Returns the vehicle type enumeration.
     *
     * @return vehicle type
     */
    public VehicleType getVehicleType() {
        return vehicleType;
    }

    /**
     * Sets the vehicle type enumeration.
     *
     * @param vehicleType vehicle type to store
     */
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Provides the passenger capacity for the vehicle.
     *
     * @return capacity value
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets the passenger capacity for the vehicle.
     *
     * @param capacity capacity value to store
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
