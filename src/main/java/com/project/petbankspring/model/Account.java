package com.project.petbankspring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
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
