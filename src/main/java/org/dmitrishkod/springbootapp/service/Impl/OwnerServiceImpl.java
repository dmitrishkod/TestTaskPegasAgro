package org.dmitrishkod.springbootapp.service.Impl;

import lombok.AllArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.OwnerDto;
import org.dmitrishkod.springbootapp.model.entity.Car;
import org.dmitrishkod.springbootapp.model.entity.Owner;
import org.dmitrishkod.springbootapp.repository.CarRepository;
import org.dmitrishkod.springbootapp.repository.DealerRepository;
import org.dmitrishkod.springbootapp.repository.OwnerRepository;
import org.dmitrishkod.springbootapp.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private  final OwnerRepository ownerRepository;
    private final DealerRepository dealerRepository;
    private final CarRepository carRepository;

    @Override
    public Owner create(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setId(owner.getId());
        owner.setFio(ownerDto.getFio());
        owner.setMail(ownerDto.getMail());
        owner.setPhone(ownerDto.getPhone());

        if (ownerDto.getDealer() != null)
            owner.setDealer(dealerRepository.findByName(ownerDto.getDealer()));

        owner.setCars(new ArrayList<>()); // Cars не подвязываем, так как изначально мы их не знаем и в таске не сказано, что их нужно создавать в месте

        ownerRepository.save(owner);

        return owner;
    }

    @Override
    public void deleteCarById(OwnerDto ownerDto,Long carId) {
        Owner owner = ownerRepository.findById(ownerDto.getId()).get();
        for (Car car : owner.getCars()){
            if (car.getId().equals(carId)){
                owner.getCars().remove(carId);
                ownerRepository.save(owner);
            }
        }
    }

    @Override
    public Owner updateCarList(OwnerDto ownerDto, Long carId) {
        Owner owner = ownerRepository.findById(ownerDto.getId()).get();
        owner.getCars().add(carRepository.findById(carId).get());
        ownerRepository.save(owner);
        return owner;
    }

    @Override
    public Optional<Owner> getById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }
}
