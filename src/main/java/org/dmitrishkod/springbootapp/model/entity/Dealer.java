package org.dmitrishkod.springbootapp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dmitrishkod.springbootapp.model.dto.CarDto;
import org.dmitrishkod.springbootapp.model.dto.DealerDto;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "dealer")
public class Dealer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mail")
    private String mail;

    @Column(name = "representative")
    private String representative;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Owner> owners;

    public static DealerDto toDto(Dealer dealer){
        return DealerDto.builder()
                .id(dealer.getId())
                .name(dealer.getName())
                .mail(dealer.getMail())
                .representative(dealer.getRepresentative())
                .owners(dealer.getOwners().stream().map(Owner::toDto).toList())
                .build();
    }
}
