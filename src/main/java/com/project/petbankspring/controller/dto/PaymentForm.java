package com.project.petbankspring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class PaymentForm {
    @NotNull
    @Pattern(regexp = "")
    @Digits(integer = 4, fraction = 0)
    private String  debit;
    @NotNull
    @Digits(integer = 4, fraction = 0)
    private String  credit;
    @DecimalMin("0.01")
    private BigDecimal amount;
    @NotNull
    @Size(min=5, max=60)
    private String description;


}
