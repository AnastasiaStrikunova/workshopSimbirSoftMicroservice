package org.example.dto;

public class CustomerRequestDto {
    private String name;
    private Long idPurse;

    public CustomerRequestDto(String name, Long idPurse) {
        this.name = name;
        this.idPurse = idPurse;
    }

    public CustomerRequestDto() {
    }

    public String getName() {
        return this.name;
    }

    public Long getIdPurse() {
        return this.idPurse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdPurse(Long idPurse) {
        this.idPurse = idPurse;
    }
}
