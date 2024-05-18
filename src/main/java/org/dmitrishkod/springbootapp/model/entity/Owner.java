package org.dmitrishkod.springbootapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.CarDto;
import org.dmitrishkod.springbootapp.model.dto.OwnerDto;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "owner")
public class Owner {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "phone")
    private String phone;

    @Column(name = "mail")
    private String mail;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Car> cars;

    @ManyToOne
    @JoinColumn(name = "dealer")
    private Dealer dealer;

    public static OwnerDto toDto(Owner owner){
       if (owner.getCars().isEmpty() && owner.getDealer() == null){
           return OwnerDto.builder()
                   .id(owner.getId())
                   .fio(owner.getFio())
                   .phone(owner.getPhone())
                   .mail(owner.getMail())
                   .dealer(owner.getDealer().getName())
                   .build();
       }
        return OwnerDto.builder()
                .id(owner.getId())
                .fio(owner.getFio())
                .phone(owner.getPhone())
                .mail(owner.getMail())
                .cars(owner.getCars().stream().map(Car::toDto).toList())
                .dealer(owner.getDealer().getName())
                .build();
    }
}
