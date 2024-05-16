package org.dmitrishkod.springbootapp.service.Impl;

import lombok.AllArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.DealerDto;
import org.dmitrishkod.springbootapp.model.entity.Car;
import org.dmitrishkod.springbootapp.model.entity.Dealer;
import org.dmitrishkod.springbootapp.model.entity.Owner;
import org.dmitrishkod.springbootapp.repository.DealerRepository;
import org.dmitrishkod.springbootapp.repository.OwnerRepository;
import org.dmitrishkod.springbootapp.service.DealerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DealerServiceImpl implements DealerService {
    private final DealerRepository dealerRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public Dealer create(DealerDto dealerDto) {
        Dealer dealer = new Dealer();
        dealer.setId(dealerDto.getId());
        dealer.setMail(dealerDto.getMail());
        dealer.setRepresentative(dealerDto.getRepresentative());
        dealer.setOwners(new ArrayList<>()); // Owner'a не подвязываем, так как изначально мы их не знаем и в таске не сказано, что их нужно создавать в месте
        dealerRepository.save(dealer);
        return dealer;
    }

    @Override
    public Optional<Dealer> getById(Long id) {
        return dealerRepository.findById(id);
    }

    @Override
    public void deleteOwnerById(DealerDto dealerDto, Long ownerId) {
        Dealer dealer = dealerRepository.findById(dealerDto.getId()).get();
        for (Owner owner : dealer.getOwners()){
            if (owner.getId().equals(ownerId)){
                dealer.getOwners().remove(ownerId);
                dealerRepository.save(dealer);
            }
        }
    }

    @Override
    public Dealer updateOwnerList(DealerDto dealerDto, Long ownerId) {
        Dealer dealer = dealerRepository.findById(dealerDto.getId()).get();
        dealer.getOwners().add(ownerRepository.findById(ownerId).get());
        dealerRepository.save(dealer);
        return dealer;
    }

    @Override
    public List<Dealer> getAll() {
        return dealerRepository.findAll();
    }
}
