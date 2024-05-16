package org.dmitrishkod.springbootapp.service;

import org.dmitrishkod.springbootapp.model.dto.OwnerDto;
import org.dmitrishkod.springbootapp.model.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    Owner create(OwnerDto ownerDto);

    void deleteCarById(OwnerDto ownerDto,Long id);

    Owner updateCarList(OwnerDto ownerDto, Long carId);

    Optional<Owner> getById(Long id);

    List<Owner> getAll();

}
