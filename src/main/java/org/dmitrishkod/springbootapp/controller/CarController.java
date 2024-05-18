package org.dmitrishkod.springbootapp.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.AllArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.CarDto;
import org.dmitrishkod.springbootapp.model.entity.Car;
import org.dmitrishkod.springbootapp.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/car")
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto car){
        return ResponseEntity.status(HttpStatus.CREATED).body(Car.toDto(carService.create(car)));
    }

    /**
     * Задание из части два. Принимаем log fail, парсим его и возврщаем километраж
     * @param file
     * @return
     */
    @PostMapping("/gps")
    public ResponseEntity<String> parseLogFile(@RequestParam(value = "file", required = false) MultipartFile file){
        return ResponseEntity.ok(carService.parseLogFile(file));
    }
}
