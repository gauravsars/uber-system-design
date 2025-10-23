package com.dehradun.cabbooking.controller;

import com.dehradun.cabbooking.entity.Discount;
import com.dehradun.cabbooking.service.DiscountService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing discount catalogue endpoints.
 */
@RestController
@RequestMapping("/api/discounts")
public class DiscountController {

    private final DiscountService discountService;

    /**
     * Builds the controller with the discount service dependency.
     *
     * @param discountService business service delivering discount lookups
     */
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    /**
     * Fetches all discounts that are currently available for use.
     *
     * @return list of active discounts
     */
    @GetMapping("/available")
    public List<Discount> getAvailableDiscounts() {
        return discountService.getAvailableDiscounts();
    }
}
