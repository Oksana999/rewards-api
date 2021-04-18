package com.ross.myrewards.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_id_generator")
    @SequenceGenerator(name = "payment_id_generator", sequenceName = "global_seq", allocationSize = 1)
    private Long payment_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private Long createdAt;

    public Payment(User user, BigDecimal amount, Long createdAt) {
        this.user = user;
        this.amount = amount;
        this.createdAt = createdAt;
    }

}
