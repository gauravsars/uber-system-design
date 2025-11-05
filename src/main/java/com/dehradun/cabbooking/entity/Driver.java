package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.DriverStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Represents professional drivers registered with the platform.
 */
@Entity
@Table(name = "drivers")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 15, unique = true)
    private String phone;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "license_number", nullable = false, length = 50, unique = true)
    private String licenseNumber;

    @Column(name = "rating", precision = 3, scale = 2, nullable = false)
    private BigDecimal rating = BigDecimal.ZERO;

    @Column(name = "status", nullable = false)
    private DriverStatus status = DriverStatus.OFFLINE;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @OneToOne(mappedBy = "driver")
    @JsonIgnoreProperties({"driver", "hibernateLazyInitializer", "handler"})
    private Vehicle vehicle;

    /**
     * Returns the generated identifier for the driver.
     *
     * @return driver primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier, primarily used by JPA.
     *
     * @param id database assigned identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Provides the full name of the driver.
     *
     * @return driver name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the driver name stored in the system.
     *
     * @param name descriptive name of the driver
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Exposes the driver's registered phone number.
     *
     * @return phone contact
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Registers or updates the driver's phone number.
     *
     * @param phone contact number to persist
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Fetches the driver's optional email.
     *
     * @return email address if specified
     */
    public String getEmail() {
        return email;
    }

    /**
     * Stores the driver's email information.
     *
     * @param email email to associate with the driver
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Supplies the license number used for verification.
     *
     * @return unique driving license identifier
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Updates the stored driving license number.
     *
     * @param licenseNumber government issued identifier
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Retrieves the current average passenger rating.
     *
     * @return rating value
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * Adjusts the driver's average rating.
     *
     * @param rating rating value to store
     */
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    /**
     * Reveals the availability state of the driver.
     *
     * @return driver status enum
     */
    public DriverStatus getStatus() {
        return status;
    }

    /**
     * Updates the driver's availability state.
     *
     * @param status status to persist
     */
    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    /**
     * Indicates whether the driver record is soft deleted.
     *
     * @return true when marked deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Changes the soft delete flag for the driver.
     *
     * @param deleted delete marker flag
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Provides the time the driver record was created.
     *
     * @return creation timestamp
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Assigns the creation timestamp, mainly in tests.
     *
     * @param createdAt timestamp of record creation
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the vehicle currently linked with the driver.
     *
     * @return assigned vehicle if any
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Links a vehicle entity to this driver.
     *
     * @param vehicle associated vehicle record
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
