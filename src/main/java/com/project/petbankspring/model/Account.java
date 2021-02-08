package com.project.petbankspring.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account extends BaseEntity {

    @NonNull
    @Column(name = "number")
    private String number;
    @NonNull
    @Column(name = "balance")
    private BigDecimal balance;
    @NonNull
    @Column(name = "condition")
    private boolean condition;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
