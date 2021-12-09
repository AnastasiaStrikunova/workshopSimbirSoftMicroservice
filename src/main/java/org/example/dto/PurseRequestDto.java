package org.example.dto;

public class PurseRequestDto {
    Long balance;

    public PurseRequestDto(Long balance) {
        this.balance = balance;
    }

    public PurseRequestDto() {
    }

    public Long getBalance() {
        return this.balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
