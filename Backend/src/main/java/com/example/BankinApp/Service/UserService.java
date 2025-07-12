package com.example.BankinApp.Service;

import com.example.BankinApp.DTO.UserDTO;
import com.example.BankinApp.Entity.User;
import com.example.BankinApp.Enum.Role;
import com.example.BankinApp.Mapper.UserMapper;
import com.example.BankinApp.Repository.UserRepository;
import com.example.BankinApp.payload.request.RegisterRequest;
import com.example.BankinApp.payload.response.MessageResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<MessageResponse> createUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }

        User n = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(n);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<MessageResponse> updateUser(
            RegisterRequest registerRequest,
            Integer id) {
        try {
            Optional<User> userOptional = userRepository.findById(Long.valueOf(id));
            User user = userOptional.get();

            Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
            if (existingUser.isPresent() && !existingUser.get().getId().equals(user.getId())) {
                return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
            }

            user.setFirstName(registerRequest.getFirstName());
            user.setLastName(registerRequest.getLastName());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("User information updated"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }

    public UserDTO getAuthUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        User user = userRepository.findByEmail(jwt.getSubject())
                .orElseThrow(() -> new RuntimeException("User not found"));
        ;
        return userMapper.convertToDTO(user);
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(user.getId().intValue());
                    userDTO.setFirstName(user.getFirstName());
                    userDTO.setLastName(user.getLastName());
                    userDTO.setEmail(user.getEmail());
                    return userDTO;
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
