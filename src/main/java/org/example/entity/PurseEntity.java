package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "purse")
public class PurseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purse_id_gen")
    @SequenceGenerator(name = "purse_id_gen", sequenceName = "seq_purse", allocationSize = 1)
    Long idPurse;
    Long balance;

    public PurseEntity(Long idPurse) {
        this.idPurse = idPurse;
    }
}
