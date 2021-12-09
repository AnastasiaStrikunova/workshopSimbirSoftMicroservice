package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

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

    public HistoryEntity(Long idHistory, CustomerEntity customerEntity, Long amount, String typeOfTransaction, Date time) {
        this.idHistory = idHistory;
        this.customerEntity = customerEntity;
        this.amount = amount;
        this.typeOfTransaction = typeOfTransaction;
        this.time = time;
    }

    public HistoryEntity() {
    }

    public Long getIdHistory() {
        return this.idHistory;
    }

    public CustomerEntity getCustomerEntity() {
        return this.customerEntity;
    }

    public Long getAmount() {
        return this.amount;
    }

    public String getTypeOfTransaction() {
        return this.typeOfTransaction;
    }

    public Date getTime() {
        return this.time;
    }

    public void setIdHistory(Long idHistory) {
        this.idHistory = idHistory;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setTypeOfTransaction(String typeOfTransaction) {
        this.typeOfTransaction = typeOfTransaction;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
