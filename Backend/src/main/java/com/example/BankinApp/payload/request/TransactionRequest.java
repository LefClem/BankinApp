package com.example.BankinApp.payload.request;

import java.util.Date;

import com.example.BankinApp.Entity.Account;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {
    public String label;
    public Double amount;
    public String type;
    public Date date;
    public Account account;
}
