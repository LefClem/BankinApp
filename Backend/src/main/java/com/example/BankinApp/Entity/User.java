package com.example.BankinApp.Entity;

import java.util.Date;

import com.example.BankinApp.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private Date created_at;

    @Column
    private Date updated_at;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}