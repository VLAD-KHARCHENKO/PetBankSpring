package com.project.petbankspring.controller.dto;

import com.project.petbankspring.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class PaymentForm {
    @NotNull
    private String  debit;
    @NotNull
    private String  credit;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String description;

    public PaymentForm() {
    }
}
