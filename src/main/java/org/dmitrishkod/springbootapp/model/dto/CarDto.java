package org.dmitrishkod.springbootapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CarDto {
    private Long id;
    private Long number;
    private LocalDate date;
    private String ownerFio;
}
