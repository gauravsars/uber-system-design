package com.dehradun.cabbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Dehradun Cab Booking Spring Boot application.
 */
@SpringBootApplication
public class CabBookingApplication {

    /**
     * Bootstraps the Spring context and launches the embedded web server.
     *
     * @param args standard JVM command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CabBookingApplication.class, args);
    }
}
