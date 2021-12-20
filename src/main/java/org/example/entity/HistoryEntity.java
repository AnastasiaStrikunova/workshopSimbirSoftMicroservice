package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "history")
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_id_gen")
    @SequenceGenerator(name = "history_id_gen", sequenceName = "seq_history", allocationSize = 1)
    Long idHistory;
    @ManyToOne
    @JoinColumn(name = "personal_account")
    @JsonBackReference
    CustomerEntity customerEntity;
    Long amount;
    @Column(name = "type_of_transaction")
    String typeOfTransaction;
    Date time;
}
