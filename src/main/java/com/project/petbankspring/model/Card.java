package com.project.petbankspring.model;

import com.project.petbankspring.model.enums.CardName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "card", uniqueConstraints = @UniqueConstraint(columnNames = "number"))
public class Card extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private CardName cardName;
    @Column(name = "number")
    private String number;
    @Column(name = "condition")
    private boolean condition;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}
