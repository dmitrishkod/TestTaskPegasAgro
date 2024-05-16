package org.dmitrishkod.springbootapp.service;

import org.dmitrishkod.springbootapp.model.dto.DealerDto;
import org.dmitrishkod.springbootapp.model.entity.Dealer;

import java.util.List;
import java.util.Optional;

public interface DealerService {
    Dealer create(DealerDto dealerDto);
    Optional<Dealer> getById(Long id);

    void deleteOwnerById(DealerDto dealerDto, Long ownerId);
    Dealer updateOwnerList(DealerDto dealerDto, Long ownerId);

    List<Dealer> getAll();

}
