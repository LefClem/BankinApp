package com.example.BankinApp.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRequest {
    public String name;
    public Double balance;
}
