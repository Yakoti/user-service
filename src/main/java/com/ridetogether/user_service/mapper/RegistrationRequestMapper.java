package com.ridetogether.user_service.mapper;

import com.ridetogether.user_service.model.RegisterRequest;
import com.ridetogether.user_service.model.User;

public class RegistrationRequestMapper {
    public static User toUser(RegisterRequest req) {
        User user = new User();

        user.setName(req.getName());
        user.setEmail(req.getEmail());
        //password is encoded in the service layer
        user.setEmail(req.getPassword());
        user.setPhone(req.getPhone());
        user.setHomeAddress(req.getHomeAddress());
        user.setOfficeAddress(req.getOfficeAddress());
        user.setPreferredArrivalStart(req.getPreferredArrivalStart());
        user.setPreferredArrivalEnd(req.getPreferredArrivalEnd());
        user.setFlexibilityMinutes(req.getFlexibilityMinutes());
        user.setFlexibilityKm(req.getFlexibilityKm());
        user.setRole(req.getRole());

        return user;
    }
}
