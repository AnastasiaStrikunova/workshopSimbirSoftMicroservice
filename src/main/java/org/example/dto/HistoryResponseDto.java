package org.example.dto;

import java.util.Date;
import java.util.UUID;

public class HistoryResponseDto {
    Long idHistory;
    UUID personalAccount;
    Long amount;
    String typeOfTransaction;
    Date time;

    public HistoryResponseDto(Long idHistory, UUID personalAccount, Long amount, String typeOfTransaction, Date time) {
        this.idHistory = idHistory;
        this.personalAccount = personalAccount;
        this.amount = amount;
        this.typeOfTransaction = typeOfTransaction;
        this.time = time;
    }

    public HistoryResponseDto() {
    }

    public Long getIdHistory() {
        return this.idHistory;
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

    public void setIdHistory(Long idHistory) {
        this.idHistory = idHistory;
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
