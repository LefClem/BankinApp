package com.example.BankinApp.Entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction")
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String label;

    @Column
    private Double amount;

    @Column
    private String type;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
