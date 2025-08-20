package com.ridetogether.user_service.repository;

import com.ridetogether.user_service.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
