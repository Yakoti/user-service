package com.ridetogether.user_service.mapper;

import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.model.Driver;

public class DriverMapper {
    public static DriverDto toDto(Driver driver) {
        return new DriverDto(
                driver.getId(),
                driver.getName(),
                driver.getEmail(),
                driver.getPhone(),
                driver.getHomeAddress(),
                driver.getOfficeAddress(),
                driver.getPreferredArrivalStart(),
                driver.getPreferredArrivalEnd(),
                driver.getFlexibilityMinutes(),
                driver.getFlexibilityKm(),
                driver.getRole(),
                driver.getAvailableSeats(),
                driver.getCostPer100KmEUR()
        );
    }

}
