package com.dehradun.cabbooking.repository;

import com.dehradun.cabbooking.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository providing CRUD access to user records.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user that is not marked as deleted.
     *
     * @param id identifier of the desired user
     * @return optional containing the user when present and active
     */
    Optional<User> findByIdAndDeletedFalse(Long id);
}
