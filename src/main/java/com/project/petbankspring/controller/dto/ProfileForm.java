package com.project.petbankspring.controller.dto;

import com.project.petbankspring.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class ProfileForm {
    @NotNull
    private String userId;
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
    @NotNull
    private String password_confirm;


}
