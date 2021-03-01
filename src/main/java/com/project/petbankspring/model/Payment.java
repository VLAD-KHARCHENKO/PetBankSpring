package com.project.petbankspring.model;

import com.project.petbankspring.model.enums.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment extends BaseEntity {

    @NonNull
    @Column(name = "date")
    @DateTimeFormat(pattern = "MM-dd HH:mm")
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

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MM-dd HH:mm"));
    }

}
