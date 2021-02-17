package com.project.petbankspring.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
public class LoginForm {
    @NotNull
    @Email
    private String username;
    @NotNull
    private String password;


}
