package com.example.BankinApp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankinApp.Entity.Account;
import com.example.BankinApp.Entity.Transaction;
import com.example.BankinApp.Repository.AccountRepository;
import com.example.BankinApp.Repository.TransactionRepository;
import com.example.BankinApp.payload.request.TransactionRequest;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(TransactionRequest transactionRequest) {
        Account account = accountRepository.findById(transactionRequest.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        System.out.println(account);
        Transaction transaction = Transaction.builder()
                .label(transactionRequest.getLabel())
                .amount(transactionRequest.getAmount())
                .type(transactionRequest.getType())
                .date(transactionRequest.getDate())
                .account(account)
                .build();
        return transactionRepository.save(transaction);
    }
}
