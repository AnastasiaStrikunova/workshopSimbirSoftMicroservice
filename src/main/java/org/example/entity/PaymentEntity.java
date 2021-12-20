package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

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
}
