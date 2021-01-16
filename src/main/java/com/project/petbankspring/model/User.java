package com.project.petbankspring.model;

import com.project.petbankspring.model.enams.Role;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String password;
    @NonNull
    @Column(unique = true)
    private String email;
    @NonNull
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
