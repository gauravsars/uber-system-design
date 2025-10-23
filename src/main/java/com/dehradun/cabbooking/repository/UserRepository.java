package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing rider records.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
