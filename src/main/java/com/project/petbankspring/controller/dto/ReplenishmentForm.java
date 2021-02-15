package com.project.petbankspring.controller.dto;

import com.project.petbankspring.model.enums.CardName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ReplenishmentForm {
    @NotNull
    private long cardNumber;
    @NotNull
    private BigDecimal amount;
}
