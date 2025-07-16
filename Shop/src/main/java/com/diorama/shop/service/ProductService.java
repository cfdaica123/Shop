package com.diorama.shop.service;

import com.diorama.shop.dto.client.request.ProductRequestDTO;
import com.diorama.shop.dto.client.response.ProductResponseDTO;
import com.diorama.shop.mapper.ProductMapper;
import com.diorama.shop.model.*;
import com.diorama.shop.repository.CategoryRepository;
import com.diorama.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final String PRODUCT_NOT_FOUND = "Product not found";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductResponseDTO> getAllProducts(boolean showAll) {
        List<Product> products = showAll
            ? productRepository.findAll()
            : productRepository.findByActiveTrue();

        return products.stream()
            .map(ProductMapper::toResponseDTO)
            .toList();
    }


    public Page<ProductResponseDTO> getAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(ProductMapper::toResponseDTO);
    }

    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND));
        return ProductMapper.toResponseDTO(product);
    }

    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = ProductMapper.toEntity(dto);
        
        // Set category if categoryId is provided
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            product.setCategory(category);
        }
        
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResponseDTO(savedProduct);
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND));
        
        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setStockQuantity(dto.getStockQuantity());
        existingProduct.setScale(dto.getScale());
        existingProduct.setDimensions(dto.getDimensions());
        existingProduct.setCategory(dto.getCategoryId() != null ? categoryRepository.findById(dto.getCategoryId()).orElse(null) : null);
        
        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.toResponseDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductResponseDTO> getByCategory(Long categoryId) {
        return productRepository.findAll().stream()
                .filter(p -> p.getCategory() != null && p.getCategory().getId().equals(categoryId))
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    public List<ProductResponseDTO> search(String name, String scale, BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findAll().stream()
                .filter(p -> (name == null || p.getName().toLowerCase().contains(name.toLowerCase())) &&
                             (scale == null || p.getScale().toLowerCase().contains(scale.toLowerCase())) &&
                             (minPrice == null || p.getPrice().compareTo(minPrice) >= 0) &&
                             (maxPrice == null || p.getPrice().compareTo(maxPrice) <= 0))
                .map(ProductMapper::toResponseDTO)
                .toList();
    }

    public void toggleActive(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND));
        product.setActive(!product.isActive());
        productRepository.save(product);
    }

    public void uploadImages(Long id, MultipartFile[] files) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND));
        List<ProductImage> images = new ArrayList<>();
        for (MultipartFile file : files) {
            ProductImage img = new ProductImage();
            img.setProduct(product);
            img.setUrl("/fake-path/" + file.getOriginalFilename());
            images.add(img);
        }
        product.setImages(images);
        productRepository.save(product);
    }
}
