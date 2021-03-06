package com.project.petbankspring.model;

import com.project.petbankspring.model.enums.CardCondition;
import com.project.petbankspring.model.enums.CardName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "card", uniqueConstraints = @UniqueConstraint(columnNames = "number"))
public class Card extends BaseEntity {

    @Enumerated(value = EnumType.STRING)
    private CardName cardName;
    @Column(name = "number")
    private Long number;
    @Enumerated(value = EnumType.STRING)
    private CardCondition condition;
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

}
