package com.diorama.shop.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Lob // Dùng cho các trường văn bản dài, sẽ map sang kiểu TEXT trong DB
    @Column(columnDefinition = "TEXT")
    private String description;
    
    // Luôn dùng BigDecimal cho tiền tệ để tránh sai số
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private int stockQuantity;

    @OneToMany(
        mappedBy = "product", 
        cascade = CascadeType.ALL, // Khi xóa Product, các ảnh liên quan cũng bị xóa
        orphanRemoval = true       // Khi xóa ảnh khỏi list, nó cũng bị xóa khỏi DB
    )
    private List<ProductImage> images = new ArrayList<>();
    
    private String scale; // Tỷ lệ: "1/35", "1/72"
    
    private String dimensions; // Kích thước: "30x20x15 cm"

    @Column(nullable = false)
    private boolean active = true; // Trạng thái hoạt động của sản phẩm

    // Nhiều Product thuộc về một Category.
    // fetch = FetchType.LAZY: Tối ưu, chỉ load Category khi cần.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id") // Tên cột khóa ngoại trong bảng products
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
    
    @CreationTimestamp // Tự động gán ngày giờ hiện tại khi tạo mới
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // Tự động cập nhật ngày giờ khi có thay đổi
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Getters and Setters...
    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return BigDecimal return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return int return the stockQuantity
     */
    public int getStockQuantity() {
        return stockQuantity;
    }

    /**
     * @param stockQuantity the stockQuantity to set
     */
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    /**
     * @return List<ProductImage> return the images
     */
    public List<ProductImage> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    /**
     * @return Category return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return List<Review> return the reviews
     */
    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * @param reviews the reviews to set
     */
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * @return LocalDateTime return the createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return LocalDateTime return the updatedAt
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return String return the scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * @param scale the scale to set
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * @return String return the dimensions
     */
    public String getDimensions() {
        return dimensions;
    }

    /**
     * @param dimensions the dimensions to set
     */
    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * @return boolean return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}