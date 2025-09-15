package com.ridetogether.user_service.service;

import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.mapper.DriverDtoMapper;
import com.ridetogether.user_service.mapper.PassengerDtoMapper;
import com.ridetogether.user_service.repository.UserRepository;
import com.ridetogether.user_service.model.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ridetogether.user_service.model.UserRole.DRIVER;
import static com.ridetogether.user_service.model.UserRole.PASSENGER;

@Service
public class DriverService {
    private final UserRepository userRepository;

    public DriverService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public Optional<DriverDto> findById(long id) {
        Optional<User> userOpt = userRepository.findById(id);
        Optional<DriverDto> dtoOpt = userOpt.map(DriverDtoMapper::toDto);
        return dtoOpt;
    }

    public Optional<DriverDto> findByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        Optional<DriverDto> dtoOpt = userOpt.map(DriverDtoMapper::toDto);
        return dtoOpt;
    }

    public List<DriverDto> findAllDrivers() {
        return userRepository.findByRole(DRIVER).stream().map(DriverDtoMapper::toDto).toList();
    }

    public List<PassengerDto> findMatchingPassengers(Long driverId) {
        Optional<User> userOpt = userRepository.findById(driverId);
        if(userOpt.isEmpty()) throw new EntityNotFoundException("There is no driver with id: " + driverId);
        User passenger = userOpt.get();

        return userRepository.findByRoleAndArrivalWindow(PASSENGER,passenger.getPreferredArrivalStart(),
                        passenger.getPreferredArrivalEnd())
                .stream().map(PassengerDtoMapper::toDto).toList();
    }

    //find matching....

// THIS SHOULD BE ONLY possible IN REGISTER
//    public DriverDto createDriverDto(DriverDto driver) {
//        return userRepository.save(driver).map(DriverDtoMapper::toDto);
//    }

    //this should be universal method in userservice that takes driverdto or passenger dto parse and change the info
//    public DriverDto updateDriverDto(DriverDto driver) {
//        return userRepository.save(driver);
//    }

//    //idk if we should even have this method...
//    public void deleteDriverDto(DriverDto driver) {
//        userRepository.deleteById(driver.getId());
//    }

}
