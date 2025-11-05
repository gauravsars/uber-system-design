package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Driver;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository managing driver persistence operations.
 */
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    /**
     * Locates an undeleted driver by identifier.
     *
     * @param id driver identifier
     * @return optional containing the driver when active
     */
    Optional<Driver> findByIdAndDeletedFalse(Long id);
}
