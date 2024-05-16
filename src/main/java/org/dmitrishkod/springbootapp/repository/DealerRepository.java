package org.dmitrishkod.springbootapp.repository;

import org.dmitrishkod.springbootapp.model.entity.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    Dealer findByName(String name);
}
