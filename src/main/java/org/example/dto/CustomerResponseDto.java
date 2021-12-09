package org.example.dto;

import java.util.UUID;

public class CustomerResponseDto {
    private UUID personalAccount;
    private String name;
    private Long idPurse;

    public CustomerResponseDto(UUID personalAccount, String name, Long idPurse) {
        this.personalAccount = personalAccount;
        this.name = name;
        this.idPurse = idPurse;
    }

    public CustomerResponseDto() {
    }

    public UUID getPersonalAccount() {
        return this.personalAccount;
    }

    public String getName() {
        return this.name;
    }

    public Long getIdPurse() {
        return this.idPurse;
    }

    public void setPersonalAccount(UUID personalAccount) {
        this.personalAccount = personalAccount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdPurse(Long idPurse) {
        this.idPurse = idPurse;
    }
}
