package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.DriverStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Captures the driver workforce operating in Dehradun.
 */
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Integer driverId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 15, unique = true)
    private String phone;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "license_number", nullable = false, length = 50, unique = true)
    private String licenseNumber;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private DriverStatus status = DriverStatus.OFFLINE;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "driver")
    @JsonBackReference("ride-vehicle")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "driver")
    @JsonBackReference("ride-driver")
    private List<Ride> rides = new ArrayList<>();

    /**
     * Provides the unique identifier for the driver.
     *
     * @return primary key of the driver record
     */
    public Integer getDriverId() {
        return driverId;
    }

    /**
     * Updates the unique identifier for the driver.
     *
     * @param driverId identifier sourced from the database sequence
     */
    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    /**
     * Retrieves the driver's full name.
     *
     * @return driver name string
     */
    public String getName() {
        return name;
    }

    /**
     * Persists the driver's full name.
     *
     * @param name textual value for the driver's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accesses the registered phone number for the driver.
     *
     * @return phone number string
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Stores the driver's contact number.
     *
     * @param phone driver phone number adhering to local format
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Reads the driver's email address when available.
     *
     * @return optional email information
     */
    public String getEmail() {
        return email;
    }

    /**
     * Assigns the driver's email address.
     *
     * @param email driver email string
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtains the driving license number for verification.
     *
     * @return transport department issued license
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Records the driver's license number for compliance.
     *
     * @param licenseNumber official driving license identifier
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Returns the cumulative rating assigned to the driver.
     *
     * @return average rating stored with two decimal precision
     */
    public BigDecimal getRating() {
        return rating;
    }

    /**
     * Sets the averaged rating for the driver's service quality.
     *
     * @param rating mean value of received ratings
     */
    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    /**
     * Provides the driver's current operational state.
     *
     * @return status enumeration value
     */
    public DriverStatus getStatus() {
        return status;
    }

    /**
     * Updates the status to reflect availability.
     *
     * @param status driver status aligned with marketplace logic
     */
    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    /**
     * Indicates whether the driver has been soft deleted.
     *
     * @return true when removed from assignments
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Flags the driver as removed without physical deletion.
     *
     * @param deleted logical deletion flag
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Supplies the creation timestamp for the driver record.
     *
     * @return creation timestamp captured when the driver signed up
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp on the driver record.
     *
     * @param createdAt persisted creation time
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Accesses the vehicle assigned to the driver.
     *
     * @return associated vehicle entity
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * Binds a vehicle to the driver.
     *
     * @param vehicle vehicle currently operated by the driver
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * Collects the rides handled by the driver.
     *
     * @return list of ride assignments
     */
    public List<Ride> getRides() {
        return rides;
    }

    /**
     * Attaches rides to the driver history.
     *
     * @param rides ride entities referencing the driver
     */
    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }
}
