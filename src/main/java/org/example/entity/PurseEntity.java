package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "purse")
public class PurseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purse_id_gen")
    @SequenceGenerator(name = "purse_id_gen", sequenceName = "seq_purse", allocationSize = 1)
    Long idPurse;
    Long balance;

    public PurseEntity(Long idPurse, Long balance) {
        this.idPurse = idPurse;
        this.balance = balance;
    }

    public PurseEntity(Long idPurse) {
        this.idPurse = idPurse;
    }

    public PurseEntity() {
    }

    public Long getIdPurse() {
        return this.idPurse;
    }

    public Long getBalance() {
        return this.balance;
    }

    public void setIdPurse(Long idPurse) {
        this.idPurse = idPurse;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
