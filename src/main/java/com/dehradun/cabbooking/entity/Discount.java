package com.dehradun.cabbooking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a promotional offer applicable to Dehradun rides.
 */
@Entity
@Table(name = "discounts")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Integer discountId;

    @Column(name = "code", nullable = false, length = 50, unique = true)
    private String code;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "percentage")
    private Integer percentage;

    @Column(name = "valid_from")
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "discounts")
    @JsonBackReference("ride-discount")
    private List<Ride> rides = new ArrayList<>();

    /**
     * Retrieves the identifier of the discount record.
     *
     * @return primary key for the discount
     */
    public Integer getDiscountId() {
        return discountId;
    }

    /**
     * Sets the identifier of the discount record.
     *
     * @param discountId database generated identifier
     */
    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    /**
     * Provides the discount code used during checkout.
     *
     * @return alphanumeric discount code
     */
    public String getCode() {
        return code;
    }

    /**
     * Stores the discount code for rider usage.
     *
     * @param code unique promotion code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Supplies a friendly description of the promotion.
     *
     * @return human readable description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Persists the description of the promotion.
     *
     * @param description textual explanation of the discount
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the discount percentage applied to the fare.
     *
     * @return percentage value between 0 and 100
     */
    public Integer getPercentage() {
        return percentage;
    }

    /**
     * Defines the discount percentage to apply for eligible rides.
     *
     * @param percentage whole number representing the discount share
     */
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    /**
     * Provides the start date from which the discount is valid.
     *
     * @return inclusive start date
     */
    public LocalDate getValidFrom() {
        return validFrom;
    }

    /**
     * Sets the start date for the promotion.
     *
     * @param validFrom start date of validity
     */
    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Provides the last date on which the discount can be redeemed.
     *
     * @return inclusive expiry date
     */
    public LocalDate getValidTo() {
        return validTo;
    }

    /**
     * Sets the expiry date of the promotion.
     *
     * @param validTo inclusive end date of validity
     */
    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    /**
     * Indicates whether the discount has been soft deleted.
     *
     * @return true when the promotion is inactive
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the promotion as inactive.
     *
     * @param deleted logical deletion flag
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Returns when the discount was created.
     *
     * @return creation timestamp maintained by the system
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Stores the creation timestamp maintained by the system.
     *
     * @param createdAt time of creation
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Exposes the rides benefiting from this discount.
     *
     * @return rides associated via ride discounts table
     */
    public List<Ride> getRides() {
        return rides;
    }

    /**
     * Sets the rides benefiting from this discount.
     *
     * @param rides ride collection linked to the discount
     */
    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }
}
