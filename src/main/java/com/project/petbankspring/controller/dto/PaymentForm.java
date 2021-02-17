package com.project.petbankspring.controller.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class PaymentForm {
    @NotNull
    @Pattern(regexp = "\\d\\d\\d\\d",message = "not the correct value of debit card")
    private String debit;
    @NotNull
    @Pattern(regexp = "\\d\\d\\d\\d",message = "not the correct value of credit card")
    private String credit;
    @DecimalMin("0.01")
    private BigDecimal amount;
    @NotNull
    @Size(min = 5, max = 60)
    private String description;


}
