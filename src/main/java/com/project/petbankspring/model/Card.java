package com.project.petbankspring.model;

import com.project.petbankspring.model.enums.CardName;

import javax.persistence.*;

import lombok.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card", uniqueConstraints = @UniqueConstraint(columnNames = "number"))
public class Card extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private CardName cardName;
    @Column(name = "number")
    private String number;
    @Column(name = "condition")
    private boolean isActive;
    @Column(name = "account_id")
    private long accountId;


}
