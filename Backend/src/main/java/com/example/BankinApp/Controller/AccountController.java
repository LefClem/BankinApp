package com.example.BankinApp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankinApp.DTO.AccountDTO;
import com.example.BankinApp.Entity.Account;
import com.example.BankinApp.Service.AccountService;
import com.example.BankinApp.payload.request.AccountRequest;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/balance/{id}")
    public ResponseEntity<Optional<Account>> getBalance(@PathVariable Long id) {
        return accountService.getBalance(id);
    }

    @PostMapping("/balance")
    public ResponseEntity<Account> createBalance(@RequestBody AccountRequest accountRequest) {
        return accountService.createBalance(accountRequest);
    }

    @GetMapping("/me")
    public ResponseEntity<List<AccountDTO>> getAccountsForAuthUser() {
        return accountService.getAccountsForAuthUser();
    }
}
