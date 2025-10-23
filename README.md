# Dehradun Cab Booking Service

Spring Boot 3 based reference implementation for a localized cab booking platform tailored for Dehradun, India. The project demonstrates how to model core marketplace entities such as users, drivers, rides, payments and discounts using Spring Data JPA mapped against a PostgreSQL schema.

## Tech stack

- Java 17
- Spring Boot 3.2
- Spring Data JPA (Hibernate)
- PostgreSQL 14+
- Maven 3.9+

## Running locally

1. Configure a PostgreSQL database and update the datasource credentials in `src/main/resources/application.yml`.
2. From the project root execute:

   ```bash
   mvn spring-boot:run
   ```

3. Visit `http://localhost:8080/api/city` to confirm the service is localized to Dehradun.

## Database schema mapping

The following tables are represented by dedicated JPA entities located under `com.dehradun.cabbooking.entity`:

- `users`
- `drivers`
- `locations`
- `discounts`
- `vehicles`
- `rides`
- `ratings`
- `payments`
- `ride_discounts` (via a `@ManyToMany` relationship between rides and discounts)

Each entity includes descriptive comments above every accessor method and uses enumerations to mirror the allowed status values defined in the schema.
