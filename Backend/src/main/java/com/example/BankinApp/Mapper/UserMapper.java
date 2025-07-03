package com.example.BankinApp.Mapper;

import org.springframework.stereotype.Component;

import com.example.BankinApp.DTO.UserDTO;
import com.example.BankinApp.Entity.User;

@Component
public class UserMapper {
    public UserDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(Math.toIntExact(user.getId()));
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        return dto;
    }

}
