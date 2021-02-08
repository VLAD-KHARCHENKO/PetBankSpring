package com.project.petbankspring.model;

import com.project.petbankspring.model.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment extends BaseEntity {
    @NonNull
    @Column(name = "date")
    private LocalDateTime date;
    @NonNull
    @OneToOne
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    private Account debit;
    @NonNull
    @OneToOne
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id")
    private Account credit;
    @NonNull
    @Column(name = "amount")
    private BigDecimal amount;
    @NonNull
    @Column(name = "description")
    private String description;
    @NonNull
    @Enumerated(value = EnumType.STRING)
    private Status status;

}
