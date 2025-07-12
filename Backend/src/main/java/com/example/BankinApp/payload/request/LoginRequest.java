package com.example.BankinApp.payload.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    public String email;
    public String password;
}
