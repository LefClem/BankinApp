package com.example.BankinApp.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.BankinApp.DTO.AccountDTO;
import com.example.BankinApp.Entity.Account;
import com.example.BankinApp.Entity.User;
import com.example.BankinApp.Mapper.AccountMapper;
import com.example.BankinApp.Repository.AccountRepository;
import com.example.BankinApp.Repository.UserRepository;
import com.example.BankinApp.payload.request.AccountRequest;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Optional<Account>> getBalance(Long id) {
        return ResponseEntity.ok(accountRepository.findById(id));
    }

    public ResponseEntity<Account> createBalance(AccountRequest accountRequest) {
        User user = userRepository.findById(Long.valueOf(userService.getAuthUser().getId()))
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = Account.builder()
                .name(accountRequest.getName())
                .balance(accountRequest.getBalance())
                .user(user)
                .build();

        return ResponseEntity.ok(accountRepository.save(account));
    }

    public ResponseEntity<List<AccountDTO>> getAccountsForAuthUser() {
        User user = userRepository.findById(Long.valueOf(userService.getAuthUser().getId()))
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Account> accounts = accountRepository.findByUserId(user.getId());
        return ResponseEntity.ok(AccountMapper.convertToDTOList(accounts));
    }
}
