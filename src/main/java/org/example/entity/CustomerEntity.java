package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

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

    public CustomerEntity(UUID personalAccount) {
        this.personalAccount = personalAccount;
    }
}
