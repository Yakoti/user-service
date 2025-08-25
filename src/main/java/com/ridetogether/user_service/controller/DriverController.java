package com.ridetogether.user_service.controller;

import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.service.DriverService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable long id){
        Optional<DriverDto> driverDto = driverService.findById(id);
        if (driverDto.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(driverDto.get());
    }

    @GetMapping
    public List<DriverDto> getAllDrivers() {
        return driverService.findAllDrivers();
    }

    @GetMapping("/{id}/matching-drivers")
    public List<PassengerDto> findMatchingPassengers(@PathVariable Long id){
            return driverService.findMatchingPassengers(id);
    }

//    @PostMapping
//    public ResponseEntity<DriverDto> createDriver(@RequestBody DriverDto driverDto) {
//        System.out.println(driverDto);
//        return  ResponseEntity.ok(driverService.createDriver(driverDto));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<DriverDto> updateDriver(@PathVariable Long id, @RequestBody DriverDto driverDto) {
//        driverService.updatedriverDto(driverDto);
//        return ResponseEntity.ok(driverService.updatedriverDto(driverDto));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteDriver(@RequestBody DriverDto driverDto) {
//        driverDtoService.deleteDriver(driverDto);
//        return ResponseEntity.noContent().build();
//    }
}
