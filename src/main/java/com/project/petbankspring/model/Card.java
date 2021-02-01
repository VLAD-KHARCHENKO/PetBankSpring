//package com.project.petbankspring.model;
//
//import com.project.petbankspring.model.enams.CardName;
//import com.project.petbankspring.model.enams.Role;
//import lombok.*;
//
//import javax.persistence.*;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString
//@Entity
//@Table(name = "card")
//public class Card {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true)
//    private Long id;
//    @NonNull
//    @Enumerated(value = EnumType.STRING)
//    private CardName cardName;
//    @NonNull
//    private String number;
//    @NonNull
//    private boolean isActive;
//    @NonNull
//    @OneToOne
//    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
//    private long accountId;
//
//}
