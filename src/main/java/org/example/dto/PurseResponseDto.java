package org.example.dto;

public class PurseResponseDto {
    Long idPurse;
    Long balance;

    public PurseResponseDto(Long idPurse, Long balance) {
        this.idPurse = idPurse;
        this.balance = balance;
    }

    public PurseResponseDto() {
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
