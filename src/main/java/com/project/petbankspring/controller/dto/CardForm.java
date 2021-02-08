package com.project.petbankspring.controller.dto;

import com.project.petbankspring.model.enums.CardName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CardForm {
    @NotNull
    private CardName cardName;

}
