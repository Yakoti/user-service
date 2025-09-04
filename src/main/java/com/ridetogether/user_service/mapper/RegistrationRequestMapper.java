package com.ridetogether.user_service.mapper;

import com.ridetogether.user_service.model.RegisterRequest;
import com.ridetogether.user_service.model.User;
import com.ridetogether.user_service.model.UserRole;

public class RegistrationRequestMapper {
    public static User toUser(RegisterRequest req) {
        User user = new User();

        user.setName(req.getName());
        user.setEmail(req.getEmail());
        //password is encoded in the service layer
        user.setPassword(req.getPassword());
        user.setPhone(req.getPhone());
        user.setHomeAddress(req.getHomeAddress());
        user.setOfficeAddress(req.getOfficeAddress());
        user.setPreferredArrivalStart(req.getPreferredArrivalStart());
        user.setPreferredArrivalEnd(req.getPreferredArrivalEnd());
        user.setFlexibilityMinutes(req.getFlexibilityMinutes());
        user.setFlexibilityKm(req.getFlexibilityKm());
        user.setRole(req.getRole());

        // Set driver-specific fields
        if (req.getRole() == UserRole.DRIVER) {
            user.setAvailableSeats(req.getAvailableSeats());
            user.setCostPer100KmEUR(req.getCostPer100KmEUR());
        } else {
            // For passengers, these fields should be 0
            user.setAvailableSeats(0);
            user.setCostPer100KmEUR(0.0);
        }

        return user;
    }
}