package com.project.petbankspring.controller.dto;

import com.project.petbankspring.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileForm {

    private String firstName;
    private String lastName;
    private boolean condition;
    private String login;
    private Role role;
    private String password;
    private String password_confirm;


}
