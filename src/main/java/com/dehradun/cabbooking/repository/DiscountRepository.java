package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Discount;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository providing access to discount codes.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    /**
     * Retrieves active discounts based on the provided date.
     *
     * @param referenceDate date used to validate availability
     * @return list of discounts active on the supplied date
     */
    @Query("SELECT d FROM Discount d WHERE d.deleted = false AND (d.validFrom IS NULL OR d.validFrom <= :referenceDate) AND (d.validTo IS NULL OR d.validTo >= :referenceDate)")
    List<Discount> findActiveDiscounts(@Param("referenceDate") LocalDate referenceDate);

    /**
     * Finds a discount by code ignoring case and ensuring it is not deleted.
     *
     * @param code discount code to look up
     * @return optional containing the discount when present
     */
    Optional<Discount> findByCodeIgnoreCaseAndDeletedFalse(String code);
}
