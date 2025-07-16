package com.diorama.shop.mapper;

import com.diorama.shop.dto.client.response.UserResponseDTO;
import com.diorama.shop.model.User;

public class UserMapper {
    // Private constructor để ngăn instantiation
    private UserMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setIsActive(user.isActive());
        return dto;
    }
}