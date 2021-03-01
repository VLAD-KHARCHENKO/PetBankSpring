package com.project.petbankspring.model;

import com.project.petbankspring.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "login", name = "users_unique_login_idx"))
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "login")
    private String login;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "condition")
    private boolean condition;
    @Enumerated(value = EnumType.STRING)
    private Role role;

}
