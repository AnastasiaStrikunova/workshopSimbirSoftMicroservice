package org.example.dto;

import java.util.Date;
import java.util.UUID;

public class HistoryRequestDto {
    UUID personalAccount;
    Long amount;
    String typeOfTransaction;
    Date time;

    public HistoryRequestDto(UUID personalAccount, Long amount, String typeOfTransaction, Date time) {
        this.personalAccount = personalAccount;
        this.amount = amount;
        this.typeOfTransaction = typeOfTransaction;
        this.time = time;
    }

    public HistoryRequestDto() {
    }

    public UUID getPersonalAccount() {
        return this.personalAccount;
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

    public void setPersonalAccount(UUID personalAccount) {
        this.personalAccount = personalAccount;
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
