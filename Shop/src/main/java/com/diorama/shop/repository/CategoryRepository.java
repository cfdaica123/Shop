package com.diorama.shop.repository;

import com.diorama.shop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
    boolean existsByName(String name);
    
    List<Category> findByNameContainingIgnoreCase(String keyword);
}
