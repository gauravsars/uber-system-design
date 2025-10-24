package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.VehicleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Models the vehicles registered by Dehradun drivers.
 */
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Integer vehicleId;

    @OneToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    @JsonBackReference("ride-vehicle")
    private Driver driver;

    @Column(name = "vehicle_number", nullable = false, length = 20, unique = true)
    private String vehicleNumber;

    @Column(name = "model", length = 100)
    private String model;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20)
    private VehicleType type;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    /**
     * Provides the identifier for the vehicle entry.
     *
     * @return primary key of the vehicle
     */
    public Integer getVehicleId() {
        return vehicleId;
    }

    /**
     * Updates the identifier for the vehicle entry.
     *
     * @param vehicleId identifier managed by the database
     */
    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Retrieves the driver associated with the vehicle.
     *
     * @return driver who operates the vehicle
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * Assigns the driver associated with the vehicle.
     *
     * @param driver driver entity to link
     */
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    /**
     * Returns the unique vehicle registration number.
     *
     * @return RTO issued vehicle number
     */
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    /**
     * Sets the unique vehicle registration number.
     *
     * @param vehicleNumber registration plate issued by transport authorities
     */
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    /**
     * Supplies the manufacturer and model information.
     *
     * @return vehicle model descriptor
     */
    public String getModel() {
        return model;
    }

    /**
     * Stores the manufacturer and model information.
     *
     * @param model descriptive model string
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the configured vehicle type.
     *
     * @return enumerated vehicle category
     */
    public VehicleType getType() {
        return type;
    }

    /**
     * Sets the vehicle type classification.
     *
     * @param type type enumeration value
     */
    public void setType(VehicleType type) {
        this.type = type;
    }

    /**
     * Provides the passenger capacity for the vehicle.
     *
     * @return number of seats available
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets the passenger capacity for the vehicle.
     *
     * @param capacity seat count available for riders
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Indicates whether the vehicle is soft deleted.
     *
     * @return true when the vehicle should be hidden
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the vehicle as logically removed from operations.
     *
     * @param deleted deletion state flag
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Accesses the timestamp of when the vehicle was registered.
     *
     * @return registration timestamp captured by the platform
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Stores the timestamp of vehicle registration.
     *
     * @param createdAt creation time set by persistence layer
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
