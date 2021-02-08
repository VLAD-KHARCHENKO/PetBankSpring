package com.project.petbankspring.controller.dto;

import com.project.petbankspring.model.Account;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class PaymentForm {
    @NonNull
    private String  cardNumber;
    @NonNull
    private String  debit;
    @NonNull
    private String  credit;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private String description;


    public PaymentForm() {

    }
}
