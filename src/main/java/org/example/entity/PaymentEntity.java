package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_gen")
    @SequenceGenerator(name = "payment_id_gen", sequenceName = "seq_payment", allocationSize = 1)
    Long idPayment;
    @Column(name = "id_project")
    Long idProject;
    Long price;
    @OneToOne
    @JoinColumn(name = "customer_personal_account")
    CustomerEntity customerEntity;
    Boolean isPaid;

    public PaymentEntity(Long idPayment, Long idProject, Long price, CustomerEntity customerEntity, Boolean isPaid) {
        this.idPayment = idPayment;
        this.idProject = idProject;
        this.price = price;
        this.customerEntity = customerEntity;
        this.isPaid = isPaid;
    }

    public PaymentEntity() {
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

    public CustomerEntity getCustomerEntity() {
        return this.customerEntity;
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

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }
}
