package com.ridetogether.user_service.service;


import com.ridetogether.user_service.dto.UserDto;
import com.ridetogether.user_service.mapper.DriverMapper;
import com.ridetogether.user_service.mapper.PassengerMapper;
import com.ridetogether.user_service.mapper.UserMapper;
import com.ridetogether.user_service.model.*;
import com.ridetogether.user_service.repository.DriverRepository;
import com.ridetogether.user_service.repository.PassengerRepository;
import com.ridetogether.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            DriverRepository driverRepository,
            PassengerRepository passengerRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.passengerRepository = passengerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserDto> findById(long id) {
        return userRepository.findById(id).map(UserMapper::toDto);
    }

    public Optional<UserDto> findByEmail(String email){
        return userRepository.findByEmail(email).map(UserMapper::toDto);
    }


    public Optional<User> findByEmailNotDto(String email){
        return userRepository.findByEmail(email);
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserDto createUser(User user) {
        return UserMapper.toDto(userRepository.save(user));
    }

    public UserDto updateUser(User user) {
        return UserMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    private List<User> findByRoleAndTimeOverlap(UserRole role, LocalTime startTime, LocalTime endTime) {
        return userRepository.findByRoleAndTimeOverlap(role, startTime, endTime);
    }

    public List<UserDto> findUsersWithMatchingSchedulesAndOppositeRole(long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) return List.of();

        User user = userOpt.get();
        UserRole oppositeRole = user.getRole() == UserRole.DRIVER ? UserRole.PASSENGER : UserRole.DRIVER;

        return findByRoleAndTimeOverlap(oppositeRole, user.getPreferredArrivalStart(), user.getPreferredArrivalEnd())
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserDto registerNewUser(RegisterRequest request) {
        User savedUser;
        if (request.getRole() == UserRole.DRIVER) {
            Driver driver = new Driver();
            mapCommonFieldsDriver(driver, request);
            return DriverMapper.toDto(driverRepository.save(driver));
        } else if (request.getRole() == UserRole.PASSENGER) {
            Passenger passenger = new Passenger();
            mapCommonFieldsPassenger(passenger, request);
            return PassengerMapper.toDto(passengerRepository.save(passenger));

        } else {
            throw new IllegalArgumentException("Unsupported role: " + request.getRole());
        }
    }

    private void mapCommonFieldsDriver(Driver driver, RegisterRequest request) {
        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setPassword(passwordEncoder.encode(request.getPassword()));
        driver.setRole(UserRole.DRIVER);
        driver.setPhone(request.getPhone());
        driver.setHomeAddress(request.getHomeAddress());
        driver.setOfficeAddress(request.getOfficeAddress());
        driver.setPreferredArrivalStart(request.getPreferredArrivalStart());
        driver.setPreferredArrivalEnd(request.getPreferredArrivalEnd());
        driver.setFlexibilityMinutes(request.getFlexibilityMinutes());
        driver.setFlexibilityKm(request.getFlexibilityKm());
        driver.setAvailableSeats(request.getAvailableSeats());
        driver.setCostPer100KmEUR(request.getCostPer100KmEUR());
    }

    private void mapCommonFieldsPassenger(Passenger passenger, RegisterRequest request) {
        passenger.setName(request.getName());
        passenger.setEmail(request.getEmail());
        passenger.setPassword(passwordEncoder.encode(request.getPassword()));
        passenger.setRole(UserRole.PASSENGER);
        passenger.setPhone(request.getPhone());
        passenger.setHomeAddress(request.getHomeAddress());
        passenger.setOfficeAddress(request.getOfficeAddress());
        passenger.setPreferredArrivalStart(request.getPreferredArrivalStart());
        passenger.setPreferredArrivalEnd(request.getPreferredArrivalEnd());
        passenger.setFlexibilityMinutes(request.getFlexibilityMinutes());
        passenger.setFlexibilityKm(request.getFlexibilityKm());
    }
}
