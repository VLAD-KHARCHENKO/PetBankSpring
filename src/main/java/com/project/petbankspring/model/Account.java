//package com.project.petbankspring.model;
//
//import com.project.petbankspring.model.enams.Role;
//import lombok.*;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//import java.util.List;
//
//@Data
//@Table(name = "account")
//public class Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", unique = true)
//    private Long id;
//    @NonNull
//    private String number;
//    @NonNull
//    private BigDecimal balance;
//    @NonNull
//    private List<Card> cards;
//    @NonNull
//    private boolean isActive;
//    @NonNull
//    @OneToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "user_id    ")
//    private long userId;
//
//}
