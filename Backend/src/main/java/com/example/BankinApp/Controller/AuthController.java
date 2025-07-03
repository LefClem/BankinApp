package com.example.BankinApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.BankinApp.DTO.UserDTO;
import com.example.BankinApp.Service.JwtService;
import com.example.BankinApp.Service.UserService;
import com.example.BankinApp.payload.request.LoginRequest;
import com.example.BankinApp.payload.request.RegisterRequest;
import com.example.BankinApp.payload.response.LoginResponse;
import com.example.BankinApp.payload.response.MessageResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping(path = "/me")
    public @ResponseBody ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(userService.getAuthUser());
    }

    @PutMapping(path = "/update")
    public @ResponseBody ResponseEntity<MessageResponse> updateUser(
            @RequestBody RegisterRequest registerRequest) {
        Integer id = userService.getAuthUser().getId();
        return userService.updateUser(registerRequest, id);
    }

    @PostMapping(path = "/register")
    public @ResponseBody ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            return userService.createUser(registerRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(path = "/login")
    public @ResponseBody ResponseEntity<LoginResponse> getToken(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password));

            String token = jwtService.generateToken(authentication);
            UserDTO userDTO = userService.getUserByEmail(loginRequest.email);

            return ResponseEntity.ok(new LoginResponse(userDTO, token));
        } catch (AuthenticationException e) {
            System.out.println("AUTH ERROR: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
