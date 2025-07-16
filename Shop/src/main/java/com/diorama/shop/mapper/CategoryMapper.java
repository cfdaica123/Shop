package com.diorama.shop.mapper;


import com.diorama.shop.dto.client.request.CategoryRequestDTO;
import com.diorama.shop.dto.client.response.CategoryResponseDTO;
import com.diorama.shop.model.Category;

public class CategoryMapper {
    public static CategoryResponseDTO toResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    public static CategoryResponseDTO toFullResponseDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        // Có thể thêm thông tin về products nếu CategoryResponseDTO có field đó
        return dto;
    }

    public static Category toEntity(CategoryRequestDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        return category;
    }
}
