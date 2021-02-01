//package com.project.petbankspring.model;
//
//import com.project.petbankspring.model.enams.CardName;
//import com.project.petbankspring.model.enams.Role;
//import com.project.petbankspring.model.enams.Status;
//import lombok.*;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString
//@Entity
//@Table(name = "payment")
//public class Payment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true)
//    private Long id;
//    @NonNull
//    private long debitAccountId;
//    @NonNull
//    private long creditAccountSd;
//    @NonNull
//    private BigDecimal amount;
//    @NonNull
//    private String description;
//    @NonNull
//    @Enumerated(value = EnumType.STRING)
//    private Status status;
//
//}
