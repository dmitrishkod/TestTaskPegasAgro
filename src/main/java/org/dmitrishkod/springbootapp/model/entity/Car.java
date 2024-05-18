package org.dmitrishkod.springbootapp.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.CarDto;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private Long number;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "owner")
    private Owner owner;

    public static CarDto toDto(Car car){
        if (car.getOwner() == null){
            return CarDto.builder()
                    .id(car.getId())
                    .number(car.getNumber())
                    .date(car.getDate())
                    .build();
        }
        return CarDto.builder()
                .id(car.getId())
                .number(car.getNumber())
                .date(car.getDate())
                .ownerFio(car.getOwner().getFio())
                .build();
    }
}
