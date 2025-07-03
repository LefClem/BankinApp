package com.example.BankinApp.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
