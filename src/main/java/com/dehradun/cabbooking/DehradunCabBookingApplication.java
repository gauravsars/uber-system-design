package com.dehradun.cabbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Dehradun specific cab booking service.
 */
@SpringBootApplication
public class DehradunCabBookingApplication {

    /**
     * Boots the Spring application that powers the Dehradun cab marketplace.
     *
     * @param args command line arguments forwarded by the JVM
     */
    public static void main(String[] args) {
        SpringApplication.run(DehradunCabBookingApplication.class, args);
    }
}
