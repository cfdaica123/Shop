package com.diorama.shop.mapper;

import java.util.stream.Collectors;

import com.diorama.shop.dto.admin.responsive.AdminResponseDTO;
import com.diorama.shop.model.Role;
import com.diorama.shop.model.User;

public class AdminMapper {
    private AdminMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static AdminResponseDTO toResponseDTO(User user) {
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setIsActive(user.isActive());
        dto.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        return dto;
    }
}
