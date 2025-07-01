package com.example.BankinApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BankinApp.Entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
