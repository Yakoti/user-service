package com.ridetogether.user_service.mapper;

import com.ridetogether.user_service.dto.UserDto;
import com.ridetogether.user_service.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getHomeAddress(),
                user.getOfficeAddress(),
                user.getPreferredArrivalStart(),
                user.getPreferredArrivalEnd(),
                user.getFlexibilityMinutes(),
                user.getFlexibilityKm(),
                user.getRole()
        );
    }
}

