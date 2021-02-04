package com.project.petbankspring.model;

import com.project.petbankspring.model.enums.Role;


import javax.persistence.*;
import java.util.Objects;
import lombok.*;

@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "login", name = "users_unique_login_idx"))
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "condition")
    private boolean condition;
    @Enumerated(value = EnumType.STRING)
    private Role role;


}
