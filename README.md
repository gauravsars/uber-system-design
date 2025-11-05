# Dehradun Cab Booking Service

Spring Boot service modeling a localized cab booking workflow for Dehradun. The project maps the provided PostgreSQL schema with JPA entities and exposes REST APIs for managing users, drivers, rides, and discounts.

## Tech Stack
- Java 17
- Spring Boot 3 (Web, Data JPA, Validation)
- Hibernate
- PostgreSQL 14+
- Maven

## Running Locally
1. Configure PostgreSQL credentials in `src/main/resources/application.properties`.
2. Ensure the schema described in the problem statement exists.
3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

## Available REST Endpoints
All endpoints are prefixed with `/api`.

### Users
- `POST /api/users` – Create a new user.
- `GET /api/users/{userId}` – Fetch details for a user.

### Drivers
- `POST /api/drivers` – Register a driver and their vehicle.
- `GET /api/drivers/{driverId}` – Fetch details for a driver.

### Rides
- `POST /api/rides` – Create a ride with pickup/drop coordinates and optional discounts.
- `GET /api/rides/{rideId}` – Fetch ride details.
- `GET /api/rides/by-date?date=YYYY-MM-DD` – List rides created on a specific date.
- `GET /api/rides/completed?weekStart=YYYY-MM-DD` – List completed rides for a given week.
- `GET /api/rides/in-progress/today` – List rides currently in progress today.
- `GET /api/rides/high-value?weekStart=YYYY-MM-DD&fare=500` – Rides in the given week with distance > 10km or fare greater than the supplied threshold.
- `GET /api/rides/by-discount/{code}` – Rides that used the specified discount code.

### Discounts
- `GET /api/discounts` – List active discounts (optional `date` query parameter to check another day).

## Enum Case Handling
Database records store enum values in lowercase (e.g., `active`, `offline`). Custom JPA attribute converters in `com.dehradun.cabbooking.persistence.converter` translate these lowercase strings into the corresponding Java enums case-insensitively, preventing `IllegalArgumentException: No enum constant ...` errors during entity loading.
