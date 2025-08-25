package com.ridetogether.user_service.service;

import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.mapper.DriverDtoMapper;
import com.ridetogether.user_service.mapper.PassengerDtoMapper;
import com.ridetogether.user_service.model.User;
import com.ridetogether.user_service.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ridetogether.user_service.model.UserRole.DRIVER;
import static com.ridetogether.user_service.model.UserRole.PASSENGER;

@Service
public class PassengerService {
    private final UserRepository userRepository;

    public PassengerService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public Optional<PassengerDto> findById(long id) {
        Optional<User> userOpt = userRepository.findById(id);
        Optional<PassengerDto> dtoOpt = userOpt.map(PassengerDtoMapper::toDto);
        return dtoOpt;
    }

    public Optional<PassengerDto> findByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        Optional<PassengerDto> dtoOpt = userOpt.map(PassengerDtoMapper::toDto);
        return dtoOpt;
    }

    public List<PassengerDto> findAllPassengers() {
        return userRepository.findByRole(PASSENGER).stream().map(PassengerDtoMapper::toDto).toList();
    }

    public List<DriverDto> findMatchingDrivers(Long passengerId) {
        Optional<User> userOpt = userRepository.findById(passengerId);
        if(userOpt.isEmpty()) throw new EntityNotFoundException("There is no passenger with id: " + passengerId);
        User passenger = userOpt.get();

        return userRepository.findByRoleAndArrivalWindow(DRIVER,passenger.getPreferredArrivalStart(),
                        passenger.getPreferredArrivalEnd())
                .stream().map(DriverDtoMapper::toDto).toList();
    }
}
