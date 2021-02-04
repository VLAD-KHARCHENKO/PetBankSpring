package com.project.petbankspring.controller.dto;

import com.project.petbankspring.model.enums.Role;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class ProfileForm {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private boolean condition;
    @NotNull
    private String login;
    @NotNull
    private Role role;
    @NotNull
    private String password;
    private String password_confirm;


}
