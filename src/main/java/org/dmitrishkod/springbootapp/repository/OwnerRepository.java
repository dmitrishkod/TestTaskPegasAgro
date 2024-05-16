package org.dmitrishkod.springbootapp.repository;

import org.dmitrishkod.springbootapp.model.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByFio(String fio);
}
