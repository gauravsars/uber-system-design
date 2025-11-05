package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.RatingParty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

/**
 * Stores feedback provided by riders or drivers after a ride.
 */
@Entity
@Table(name = "ratings")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @Column(name = "given_by", length = 20)
    private RatingParty givenBy;

    @Column(name = "given_to", length = 20)
    private RatingParty givenTo;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comments")
    private String comments;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    /**
     * Provides the unique identifier for the rating record.
     *
     * @return rating primary key
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the identifier value, for use by persistence providers.
     *
     * @param id rating identifier to assign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the ride that the rating is associated with.
     *
     * @return ride entity reference
     */
    public Ride getRide() {
        return ride;
    }

    /**
     * Associates a ride entity with the rating.
     *
     * @param ride ride entity to link
     */
    public void setRide(Ride ride) {
        this.ride = ride;
    }

    /**
     * Supplies the party that issued the rating.
     *
     * @return rating party enum
     */
    public RatingParty getGivenBy() {
        return givenBy;
    }

    /**
     * Sets the party that issued the rating.
     *
     * @param givenBy rating party enum to store
     */
    public void setGivenBy(RatingParty givenBy) {
        this.givenBy = givenBy;
    }

    /**
     * Supplies the party that the rating targets.
     *
     * @return rating party enum
     */
    public RatingParty getGivenTo() {
        return givenTo;
    }

    /**
     * Sets the party that the rating targets.
     *
     * @param givenTo rating party enum to store
     */
    public void setGivenTo(RatingParty givenTo) {
        this.givenTo = givenTo;
    }

    /**
     * Returns the numeric rating value.
     *
     * @return rating score
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets the numeric rating value.
     *
     * @param rating score to persist
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Provides optional comments stored with the rating.
     *
     * @return textual feedback
     */
    public String getComments() {
        return comments;
    }

    /**
     * Updates the textual feedback for the rating.
     *
     * @param comments comment text to store
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Returns the creation timestamp when the rating was stored.
     *
     * @return creation timestamp
     */
    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp for the rating.
     *
     * @param createdAt timestamp to persist
     */
    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Indicates whether the rating has been soft deleted.
     *
     * @return true when deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the rating as deleted or restores it.
     *
     * @param deleted delete flag to persist
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
