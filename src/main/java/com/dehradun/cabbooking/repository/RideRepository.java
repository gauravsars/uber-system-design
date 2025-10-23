package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Ride;
import com.dehradun.cabbooking.enums.RideStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for booking lifecycle management.
 */
@Repository
public interface RideRepository extends JpaRepository<Ride, Integer> {

    /**
     * Retrieves rides created within the provided time window.
     *
     * @param start start instant of the window
     * @param end end instant of the window
     * @return rides created during the interval
     */
    List<Ride> findByDeletedFalseAndCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    /**
     * Retrieves rides with the supplied status that finished inside the interval.
     *
     * @param status ride lifecycle status
     * @param start start instant of the window
     * @param end end instant of the window
     * @return rides matching the criteria
     */
    List<Ride> findByDeletedFalseAndStatusAndEndTimeBetween(RideStatus status, LocalDateTime start, LocalDateTime end);

    /**
     * Loads rides in any of the provided statuses created during the interval.
     *
     * @param statuses lifecycle status collection
     * @param start start instant of the window
     * @param end end instant of the window
     * @return rides matching the criteria
     */
    List<Ride> findByDeletedFalseAndStatusInAndCreatedAtBetween(Collection<RideStatus> statuses, LocalDateTime start,
        LocalDateTime end);

    /**
     * Retrieves rides where the distance or fare exceeds the configured thresholds.
     *
     * @param start start instant of the week window
     * @param end end instant of the week window
     * @param distanceThreshold minimum distance threshold
     * @param fareThreshold minimum fare threshold
     * @return rides satisfying the high distance or high fare criteria
     */
    @Query("select r from Ride r where r.deleted = false and r.createdAt between :start and :end "
        + "and ((r.distanceKm is not null and r.distanceKm > :distanceThreshold) "
        + "or (r.fare is not null and r.fare > :fareThreshold))")
    List<Ride> findRidesByDistanceOrFare(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end,
        @Param("distanceThreshold") BigDecimal distanceThreshold, @Param("fareThreshold") BigDecimal fareThreshold);

    /**
     * Retrieves rides that utilised the supplied discount code.
     *
     * @param code discount code applied to the ride
     * @return rides tagged with the discount
     */
    @Query("select distinct r from Ride r join r.discounts d where r.deleted = false and d.code = :code")
    List<Ride> findActiveRidesByDiscountCode(@Param("code") String code);
}
