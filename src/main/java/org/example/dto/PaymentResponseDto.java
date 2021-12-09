package org.example.dto;

import java.util.UUID;

public class PaymentResponseDto {
    Long idPayment;
    Long idProject;
    Long price;
    UUID personalAccount;
    Boolean isPaid;

    public PaymentResponseDto(Long idPayment, Long idProject, Long price, UUID personalAccount, Boolean isPaid) {
        this.idPayment = idPayment;
        this.idProject = idProject;
        this.price = price;
        this.personalAccount = personalAccount;
        this.isPaid = isPaid;
    }

    public PaymentResponseDto() {
    }

    public Long getIdPayment() {
        return this.idPayment;
    }

    public Long getIdProject() {
        return this.idProject;
    }

    public Long getPrice() {
        return this.price;
    }

    public UUID getPersonalAccount() {
        return this.personalAccount;
    }

    public Boolean getIsPaid() {
        return this.isPaid;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setPersonalAccount(UUID personalAccount) {
        this.personalAccount = personalAccount;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
}
