package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for ride ratings.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
