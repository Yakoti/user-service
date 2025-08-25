package com.ridetogether.user_service.repository;

import com.ridetogether.user_service.model.User;
import com.ridetogether.user_service.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRole(UserRole role);

    @Query("SELECT u FROM User u WHERE u.role = :role AND " +
            "((u.preferredArrivalStart <= :endTime AND u.preferredArrivalEnd >= :startTime))")
    List<User> findByRoleAndArrivalWindow(@Param("role") UserRole role,
                                        @Param("startTime") LocalTime startTime,
                                        @Param("endTime") LocalTime endTime);


    Optional<User> findByEmail(String email);
}
