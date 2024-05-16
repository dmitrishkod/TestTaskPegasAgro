package org.dmitrishkod.springbootapp.controller;

import org.dmitrishkod.springbootapp.model.dto.CarDto;
import org.dmitrishkod.springbootapp.model.entity.Car;
import org.dmitrishkod.springbootapp.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private CarService carService;

    @PostMapping
    public ResponseEntity<CarDto> createDealer(@RequestBody CarDto car){
        return ResponseEntity.status(HttpStatus.CREATED).body(Car.toDto(carService.create(car)));
    }
}
