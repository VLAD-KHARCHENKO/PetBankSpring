package com.project.petbankspring.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class RegistrationForm {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    private String login;
    @NotNull
    private String password;
    private String password_confirm;


}
