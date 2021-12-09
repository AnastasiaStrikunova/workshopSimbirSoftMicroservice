package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID personalAccount;
    private String name;
    @OneToOne
    @JoinColumn(name = "id_purse")
    private PurseEntity purseEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customerEntity")
    @JsonManagedReference
    private List<HistoryEntity> historyEntityList;

    public CustomerEntity(UUID personalAccount, String name, PurseEntity purseEntity, List<HistoryEntity> historyEntityList) {
        this.personalAccount = personalAccount;
        this.name = name;
        this.purseEntity = purseEntity;
        this.historyEntityList = historyEntityList;
    }

    public CustomerEntity(UUID personalAccount) {
        this.personalAccount = personalAccount;
    }

    public CustomerEntity() {
    }

    public UUID getPersonalAccount() {
        return this.personalAccount;
    }

    public String getName() {
        return this.name;
    }

    public PurseEntity getPurseEntity() {
        return this.purseEntity;
    }

    public List<HistoryEntity> getHistoryEntityList() {
        return this.historyEntityList;
    }

    public void setPersonalAccount(UUID personalAccount) {
        this.personalAccount = personalAccount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurseEntity(PurseEntity purseEntity) {
        this.purseEntity = purseEntity;
    }

    public void setHistoryEntityList(List<HistoryEntity> historyEntityList) {
        this.historyEntityList = historyEntityList;
    }
}
