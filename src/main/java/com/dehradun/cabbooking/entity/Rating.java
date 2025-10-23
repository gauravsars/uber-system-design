package com.dehradun.cabbooking.entity;

import com.dehradun.cabbooking.enums.RatingActor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 * Stores feedback shared between riders and drivers.
 */
@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer ratingId;

    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false)
    private Ride ride;

    @Enumerated(EnumType.STRING)
    @Column(name = "given_by", length = 20)
    private RatingActor givenBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "given_to", length = 20)
    private RatingActor givenTo;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comments")
    private String comments;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    /**
     * Returns the unique identifier for the rating.
     *
     * @return rating primary key
     */
    public Integer getRatingId() {
        return ratingId;
    }

    /**
     * Sets the unique identifier for the rating.
     *
     * @param ratingId rating primary key
     */
    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    /**
     * Retrieves the ride to which the rating applies.
     *
     * @return ride entity rated by the user or driver
     */
    public Ride getRide() {
        return ride;
    }

    /**
     * Associates the ride to which the rating applies.
     *
     * @param ride ride entity rated by the user or driver
     */
    public void setRide(Ride ride) {
        this.ride = ride;
    }

    /**
     * Provides information about the rating originator.
     *
     * @return enumeration describing the rating source
     */
    public RatingActor getGivenBy() {
        return givenBy;
    }

    /**
     * Sets information about the rating originator.
     *
     * @param givenBy enumeration describing the rating source
     */
    public void setGivenBy(RatingActor givenBy) {
        this.givenBy = givenBy;
    }

    /**
     * Provides information about the rating recipient.
     *
     * @return enumeration describing the rating recipient
     */
    public RatingActor getGivenTo() {
        return givenTo;
    }

    /**
     * Sets information about the rating recipient.
     *
     * @param givenTo enumeration describing the rating recipient
     */
    public void setGivenTo(RatingActor givenTo) {
        this.givenTo = givenTo;
    }

    /**
     * Returns the numeric rating value provided.
     *
     * @return rating from one to five
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * Sets the numeric rating value provided.
     *
     * @param rating rating from one to five
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    /**
     * Provides optional comments accompanying the rating.
     *
     * @return comment text
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets optional comments accompanying the rating.
     *
     * @param comments comment text
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Returns the timestamp when the rating was created.
     *
     * @return creation timestamp stored with the rating
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the timestamp when the rating was created.
     *
     * @param createdAt creation timestamp stored with the rating
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Indicates whether the rating has been soft deleted.
     *
     * @return true when rating should be ignored
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Marks the rating as logically removed.
     *
     * @param deleted logical deletion flag
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
