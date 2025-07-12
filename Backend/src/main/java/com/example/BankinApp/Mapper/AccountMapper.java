package com.example.BankinApp.Mapper;

import java.util.List;

import com.example.BankinApp.DTO.AccountDTO;
import com.example.BankinApp.Entity.Account;

public class AccountMapper {
    public static AccountDTO convertToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(Math.toIntExact(account.getId()));
        dto.setName(account.getName());
        dto.setBalance(account.getBalance());
        return dto;
    }

    public static List<AccountDTO> convertToDTOList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountMapper::convertToDTO)
                .toList();
    }
}
