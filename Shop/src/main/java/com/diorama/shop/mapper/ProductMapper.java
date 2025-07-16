package com.diorama.shop.mapper;


import com.diorama.shop.dto.client.request.ProductRequestDTO;
import com.diorama.shop.dto.client.response.ProductResponseDTO;
import com.diorama.shop.model.*;

public class ProductMapper {

    // Private constructor để hide implicit public constructor
    private ProductMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static ProductResponseDTO toResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setScale(product.getScale());
        dto.setDimensions(product.getDimensions());
        dto.setCategoryId(product.getCategory() != null ? product.getCategory().getId() : null);
        dto.setCategoryName(product.getCategory() != null ? product.getCategory().getName() : null);
        dto.setImageUrls(product.getImages().stream().map(ProductImage::getUrl).toList());
        dto.setCreatedAt(product.getCreatedAt());
        dto.setUpdatedAt(product.getUpdatedAt());
        return dto;
    }

    public static Product toEntity(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setScale(dto.getScale());
        product.setDimensions(dto.getDimensions());
        return product;
    }
}