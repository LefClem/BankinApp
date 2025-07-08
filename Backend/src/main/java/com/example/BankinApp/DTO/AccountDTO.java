package com.example.BankinApp.DTO;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer id;
    private String name;
    private Double balance;
}
