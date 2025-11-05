package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Ride;
import com.dehradun.cabbooking.enums.RideStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for querying ride lifecycle data.
 */
@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    /**
     * Locates an undeleted ride by identifier.
     *
     * @param id ride identifier to retrieve
     * @return optional containing the ride when present
     */
    Optional<Ride> findByIdAndDeletedFalse(Long id);

    /**
     * Finds all rides created between the supplied timestamps.
     *
     * @param start start of the interval (inclusive)
     * @param end end of the interval (exclusive)
     * @return rides created in the given time range
     */
    @Query("SELECT r FROM Ride r WHERE r.deleted = false AND r.createdAt >= :start AND r.createdAt < :end")
    List<Ride> findCreatedBetween(@Param("start") OffsetDateTime start, @Param("end") OffsetDateTime end);

    /**
     * Retrieves rides with the specified status in the provided window.
     *
     * @param status ride status to filter by
     * @param start window start (inclusive)
     * @param end window end (exclusive)
     * @return rides matching the status and time constraints
     */
    @Query("SELECT r FROM Ride r WHERE r.deleted = false AND r.status = :status AND r.createdAt >= :start AND r.createdAt < :end")
    List<Ride> findByStatusWithin(@Param("status") RideStatus status, @Param("start") OffsetDateTime start, @Param("end") OffsetDateTime end);

    /**
     * Retrieves rides that are currently in progress based on accepted or ongoing statuses.
     *
     * @param start window start (inclusive)
     * @param end window end (exclusive)
     * @return rides currently in progress during the supplied window
     */
    @Query("SELECT r FROM Ride r WHERE r.deleted = false AND r.status IN (:statuses) AND r.createdAt >= :start AND r.createdAt < :end")
    List<Ride> findInProgress(
        @Param("statuses") List<RideStatus> statuses,
        @Param("start") OffsetDateTime start,
        @Param("end") OffsetDateTime end
    );

    /**
     * Retrieves rides exceeding either the distance or fare thresholds within the given interval.
     *
     * @param start window start (inclusive)
     * @param end window end (exclusive)
     * @param distanceThreshold distance threshold to compare against
     * @param fareThreshold fare threshold to compare against
     * @return rides that breach either threshold within the interval
     */
    @Query("SELECT r FROM Ride r WHERE r.deleted = false AND r.createdAt >= :start AND r.createdAt < :end AND (r.distanceKm > :distanceThreshold OR r.fare > :fareThreshold)")
    List<Ride> findHighValueRides(@Param("start") OffsetDateTime start, @Param("end") OffsetDateTime end, @Param("distanceThreshold") BigDecimal distanceThreshold, @Param("fareThreshold") BigDecimal fareThreshold);
}
