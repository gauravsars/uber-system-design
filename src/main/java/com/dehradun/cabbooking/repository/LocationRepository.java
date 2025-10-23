package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for location coordinates.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
