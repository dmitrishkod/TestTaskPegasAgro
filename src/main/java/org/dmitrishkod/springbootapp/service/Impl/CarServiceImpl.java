package org.dmitrishkod.springbootapp.service.Impl;

import lombok.AllArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.CarDto;
import org.dmitrishkod.springbootapp.model.entity.Car;
import org.dmitrishkod.springbootapp.repository.CarRepository;
import org.dmitrishkod.springbootapp.repository.OwnerRepository;
import org.dmitrishkod.springbootapp.service.CarService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public Car create(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setNumber(carDto.getNumber());
        car.setDate(carDto.getDate());

        if (carDto.getOwnerFio() != null)
            ownerRepository.findByFio(carDto.getOwnerFio());

        carRepository.save(car);

        return car;
    }

    @Override
    public Optional<Car> getById(Long id) {
        return carRepository.findById(id);
    }
}
