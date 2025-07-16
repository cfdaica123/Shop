package com.diorama.shop.service;

import com.diorama.shop.dto.client.request.CategoryRequestDTO;
import com.diorama.shop.dto.client.response.CategoryResponseDTO;
import com.diorama.shop.mapper.CategoryMapper;
import com.diorama.shop.model.Category;
import com.diorama.shop.model.Product;
import com.diorama.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private static final String CATEGORY_NOT_FOUND = "Category not found";

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> getAllCategories(boolean showAll) {
        List<Category> categories = showAll
                ? categoryRepository.findAll()
                : categoryRepository.findAll().stream()
                        .filter(Category::isActive)
                        .toList();
        return categories.stream()
                .map(CategoryMapper::toResponseDTO)
                .toList();
    }

    public Page<CategoryResponseDTO> getAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable)
                .map(CategoryMapper::toResponseDTO);
    }

    public CategoryResponseDTO getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND));
        return CategoryMapper.toResponseDTO(category);
    }

    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    public List<CategoryResponseDTO> searchByName(String keyword) {
        return categoryRepository.findByNameContainingIgnoreCase(keyword)
                .stream().filter(Category::isActive)
                .map(CategoryMapper::toResponseDTO)
                .toList();
    }

    public CategoryResponseDTO getWithProducts(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND));
        return CategoryMapper.toFullResponseDTO(category);
    }

    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        if (categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Category already exists");
        }
        Category category = CategoryMapper.toEntity(dto);
        category.setActive(true);
        return CategoryMapper.toResponseDTO(categoryRepository.save(category));
    }

    public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND));

        if (!existingCategory.getName().equals(dto.getName()) &&
                categoryRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Category name already exists");
        }

        existingCategory.setName(dto.getName());
        Category updatedCategory = categoryRepository.save(existingCategory);
        return CategoryMapper.toResponseDTO(updatedCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public void softDelete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND));
        category.setActive(false);
        categoryRepository.save(category);
    }

    public void restore(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND));
        category.setActive(true);
        categoryRepository.save(category);
    }

    public void toggleActive(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND));
        category.setActive(!category.isActive());
        categoryRepository.save(category);
    }
}
