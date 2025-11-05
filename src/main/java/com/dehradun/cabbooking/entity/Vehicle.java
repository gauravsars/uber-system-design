package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

/**
 * Represents a vehicle that can be assigned to fulfill rides.
 */
@Entity
@Table(name = "vehicles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", unique = true)
    private Driver driver;

    @Column(name = "vehicle_number", nullable = false, length = 20, unique = true)
    private String vehicleNumber;

    @Column(name = "model", length = 100)
    private String model;

    @Column(name = "type", length = 20)
    private VehicleType type;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    /**
     * Provides the generated identifier of the vehicle record.
     *
     * @return vehicle primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier primarily for the persistence provider.
     *
     * @param id identifier assigned by the database
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the driver associated with the vehicle.
     *
     * @return driver entity if attached
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Associates a driver with the vehicle.
     *
     * @param driver driver entity to link
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Provides the unique registration number of the vehicle.
     *
     * @return registration identifier
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Updates the vehicle registration number.
     *
     * @param vehicleNumber registration identifier to persist
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * Retrieves the model description for the vehicle.
     *
     * @return vehicle model text
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the vehicle model description.
     *
     * @param model descriptive model name
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Exposes the vehicle category configured for the ride platform.
     *
     * @return vehicle type enum
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Updates the vehicle category classification.
     *
     * @param type vehicle type to assign
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * Provides the passenger capacity of the vehicle.
     *
     * @return number of supported passengers
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Adjusts the passenger capacity figure.
     *
     * @param capacity capacity value to persist
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Indicates whether the vehicle record is soft deleted.
     *
     * @return true when flagged as deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the vehicle as deleted or active.
     *
     * @param deleted delete flag to persist
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Provides the creation timestamp of the vehicle entry.
     *
     * @return record creation time
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp for testing or manual control.
     *
     * @param createdAt creation timestamp to store
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
