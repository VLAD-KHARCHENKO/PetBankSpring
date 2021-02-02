package com.project.petbankspring.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class LoginForm {
    @NotNull
    private String username;
    @NotNull
    private String password;


}
