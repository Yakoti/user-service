package com.ridetogether.user_service.controller;

import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/passengers")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable long id){
        Optional<PassengerDto> driverDto = passengerService.findById(id);
        if (driverDto.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(driverDto.get());
    }

    @GetMapping
    public List<PassengerDto> getAllPassengers() {
        return passengerService.findAllPassengers();
    }

    @GetMapping("/{id}/matching-drivers")
    public List<DriverDto> findMatchingDrivers(@PathVariable Long id){
        return passengerService.findMatchingDrivers(id);
    }

//    @PostMapping
//    public ResponseEntity<PassengerDto> createPassenger(@RequestBody PassengerDto driverDto) {
//        System.out.println(driverDto);
//        return  ResponseEntity.ok(driverService.createPassenger(driverDto));
//    }

//

//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePassenger(@RequestBody PassengerDto driverDto) {
//        driverDtoService.deletePassenger(driverDto);
//        return ResponseEntity.noContent().build();
//    }

}
