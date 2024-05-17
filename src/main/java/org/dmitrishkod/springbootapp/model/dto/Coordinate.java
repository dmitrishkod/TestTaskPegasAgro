package org.dmitrishkod.springbootapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coordinate {
    private Double latitude;
    private Double longitude;
    private Double speed;
}
