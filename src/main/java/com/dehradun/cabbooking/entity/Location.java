package com.dehradun.cabbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a geospatial point in Dehradun used for pickups and drops.
 */
@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "latitude", precision = 9, scale = 6, nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6, nullable = false)
    private BigDecimal longitude;

    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @OneToMany(mappedBy = "pickupLocation")
    private List<Ride> pickups = new ArrayList<>();

    @OneToMany(mappedBy = "dropLocation")
    private List<Ride> drops = new ArrayList<>();

    /**
     * Gets the identifier of the location entry.
     *
     * @return primary key value
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * Sets the identifier of the location entry.
     *
     * @param locationId primary key managed by the database
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    /**
     * Returns the latitude coordinate of the point.
     *
     * @return latitude in decimal degrees
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * Stores the latitude coordinate for the point.
     *
     * @param latitude decimal degree representation
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * Returns the longitude coordinate of the point.
     *
     * @return longitude in decimal degrees
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * Stores the longitude coordinate for the point.
     *
     * @param longitude decimal degree representation
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * Provides the timestamp when this location snapshot was captured.
     *
     * @return recorded timestamp in local server time
     */
    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    /**
     * Records the timestamp for this location snapshot.
     *
     * @param recordedAt moment when data was stored
     */
    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    /**
     * Indicates whether the location has been soft deleted.
     *
     * @return true when location should be ignored
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the location as inactive without removal.
     *
     * @param deleted deletion flag
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Lists rides that originate from this location.
     *
     * @return rides that use the location as pickup
     */
    public List<Ride> getPickups() {
        return pickups;
    }

    /**
     * Assigns the rides originating from this location.
     *
     * @param pickups ride collection referencing this location
     */
    public void setPickups(List<Ride> pickups) {
        this.pickups = pickups;
    }

    /**
     * Lists rides that complete at this location.
     *
     * @return rides that use the location as drop
     */
    public List<Ride> getDrops() {
        return drops;
    }

    /**
     * Assigns the rides ending at this location.
     *
     * @param drops ride collection referencing this location
     */
    public void setDrops(List<Ride> drops) {
        this.drops = drops;
    }
}
