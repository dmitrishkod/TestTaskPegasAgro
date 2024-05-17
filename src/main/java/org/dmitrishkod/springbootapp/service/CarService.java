package org.dmitrishkod.springbootapp.service;

import org.dmitrishkod.springbootapp.model.dto.CarDto;
import org.dmitrishkod.springbootapp.model.entity.Car;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface CarService {
    Car create(CarDto carDto);
    Optional<Car> getById(Long id);
    String parseLogFile(MultipartFile file);
}
