package com.example.BankinApp.payload.response;

import com.example.BankinApp.DTO.UserDTO;

public class LoginResponse {
    private String token;

    public LoginResponse(UserDTO user, String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
