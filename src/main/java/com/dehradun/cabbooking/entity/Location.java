package com.dehradun.cabbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

/**
 * Stores latitude and longitude coordinates representing pickup or drop points.
 */
@Entity
@Table(name = "locations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long id;

    @Column(name = "latitude", nullable = false, precision = 9, scale = 6)
    private double latitude;

    @Column(name = "longitude", nullable = false, precision = 9, scale = 6)
    private double longitude;

    @Column(name = "recorded_at", nullable = false)
    private OffsetDateTime recordedAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    /**
     * Returns the generated identifier for the location record.
     *
     * @return location primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier for persistence frameworks.
     *
     * @param id database assigned identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Provides the latitude component of the coordinate.
     *
     * @return latitude in decimal degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Updates the latitude value for the location.
     *
     * @param latitude coordinate latitude in decimal degrees
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Retrieves the longitude coordinate stored.
     *
     * @return longitude in decimal degrees
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Modifies the longitude component for the location.
     *
     * @param longitude coordinate longitude in decimal degrees
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Exposes the timestamp when the coordinate was recorded.
     *
     * @return recorded time
     */
    public OffsetDateTime getRecordedAt() {
        return recordedAt;
    }

    /**
     * Adjusts the recorded timestamp to a supplied value.
     *
     * @param recordedAt timestamp representing when coordinates were captured
     */
    public void setRecordedAt(OffsetDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    /**
     * Indicates whether the location entry is soft deleted.
     *
     * @return true when marked deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Sets the soft delete marker for the location entry.
     *
     * @param deleted delete flag value
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
