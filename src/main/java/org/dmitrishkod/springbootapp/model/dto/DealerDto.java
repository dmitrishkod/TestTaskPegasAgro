package org.dmitrishkod.springbootapp.model.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.dmitrishkod.springbootapp.model.entity.Owner;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class DealerDto {
    private Long id;
    private String name;
    private String mail;
    private String representative;
    private List<OwnerDto> owners;
}
