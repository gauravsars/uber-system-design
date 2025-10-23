package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.Discount;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository for ride discount promotions.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    /**
     * Retrieves discount definitions that are active on the provided date.
     *
     * @param today date to evaluate validity against
     * @return collection of valid discounts
     */
    @Query("select d from Discount d where d.deleted = false and "
        + "(d.validFrom is null or d.validFrom <= :today) and (d.validTo is null or d.validTo >= :today)")
    List<Discount> findActiveDiscounts(@Param("today") LocalDate today);

    /**
     * Finds an active discount by its unique code.
     *
     * @param code unique discount code
     * @return optional discount when present
     */
    Optional<Discount> findByCodeAndDeletedFalse(String code);

    /**
     * Retrieves active discounts matching the provided codes.
     *
     * @param codes collection of discount codes
     * @return list of active discounts
     */
    List<Discount> findByCodeInAndDeletedFalse(Collection<String> codes);
}
