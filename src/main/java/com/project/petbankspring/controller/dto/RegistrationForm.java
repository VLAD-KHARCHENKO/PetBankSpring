package com.project.petbankspring.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {
    @NotNull
    @Size(min=2, max=30)
    private String firstName;
    @NotNull
    private String lastName;

    @NotNull
    @Email
    private String login;
    @NotNull
    private String password;
    private String password_confirm;


}
