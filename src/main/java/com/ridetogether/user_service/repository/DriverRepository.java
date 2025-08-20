package com.ridetogether.user_service.repository;

import com.ridetogether.user_service.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
