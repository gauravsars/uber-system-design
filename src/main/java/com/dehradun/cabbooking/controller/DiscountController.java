package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.entity.Discount;
import com.dehradun.cabbooking.service.DiscountService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes REST endpoints for querying discount availability.
 */
@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     * Retrieves discounts active on the provided date.
     *
     * @param date date to evaluate discount availability
     * @return discounts valid on the provided date
     */
    @GetMapping
    public List<Discount> getAvailableDiscounts(
        @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        LocalDate reference = date != null ? date : LocalDate.now();
        return discountService.getAvailableDiscounts(reference);
    }
}
