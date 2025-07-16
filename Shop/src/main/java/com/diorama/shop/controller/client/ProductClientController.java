package com.diorama.shop.controller.client;

import com.diorama.shop.dto.client.response.ProductResponseDTO;
import com.diorama.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductClientController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts(false));
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<ProductResponseDTO>> getAllPaged(@RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(productService.getAllPaged(page, size));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProduct(@RequestParam(required = false) String name,
                                                                   @RequestParam(required = false) String scale,
                                                                   @RequestParam(required = false) BigDecimal minPrice,
                                                                   @RequestParam(required = false) BigDecimal maxPrice) {
        return ResponseEntity.ok(productService.search(name, scale, minPrice, maxPrice));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getByCategory(categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
