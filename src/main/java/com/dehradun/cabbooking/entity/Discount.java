package com.dehradun.cabbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.time.LocalDate;

/**
 * Represents promotional discount codes that can be attached to rides.
 */
@Entity
@Table(name = "discounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long id;

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

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    /**
     * Returns the generated identifier for the discount.
     *
     * @return discount primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier, typically used by persistence providers.
     *
     * @param id database identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Provides the unique discount code string.
     *
     * @return promotional code
     */
    public String getCode() {
        return code;
    }

    /**
     * Updates the unique discount code string.
     *
     * @param code promotional identifier to persist
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves the human friendly description for the discount.
     *
     * @return descriptive text
     */
    public String getDescription() {
        return description;
    }

    /**
     * Assigns descriptive text for the discount.
     *
     * @param description textual explanation
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the percentage value of the discount, if specified.
     *
     * @return discount percentage
     */
    public Integer getPercentage() {
        return percentage;
    }

    /**
     * Sets the percentage value for the discount.
     *
     * @param percentage discount percentage to store
     */
    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    /**
     * Provides the date from which the discount is valid.
     *
     * @return start date of promotion
     */
    public LocalDate getValidFrom() {
        return validFrom;
    }

    /**
     * Adjusts the valid-from date of the discount.
     *
     * @param validFrom start date to persist
     */
    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Gives the date until which the discount remains active.
     *
     * @return discount end date
     */
    public LocalDate getValidTo() {
        return validTo;
    }

    /**
     * Sets the end date controlling discount validity.
     *
     * @param validTo end date to store
     */
    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }

    /**
     * Indicates whether the discount has been soft deleted.
     *
     * @return true when deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the discount as deleted or active.
     *
     * @param deleted delete flag value
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Returns the creation timestamp for the discount entry.
     *
     * @return creation timestamp
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp on the entity instance.
     *
     * @param createdAt creation timestamp to persist
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
