package org.dmitrishkod.springbootapp.model.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dmitrishkod.springbootapp.model.entity.Car;
import org.dmitrishkod.springbootapp.model.entity.Dealer;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OwnerDto {
    private Long id;
    private String fio;
    private String phone;
    private String mail;
    private List<CarDto> carDtos;
    private String dealer;
}
