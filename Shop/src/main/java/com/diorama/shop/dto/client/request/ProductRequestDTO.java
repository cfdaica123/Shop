package com.diorama.shop.dto.client.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public class ProductRequestDTO {
    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal price;

    private int stockQuantity;

    private String scale;

    private String dimensions;

    private Long categoryId;

    private List<String> imageUrls;
    
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
     * @return Long return the categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return List<String> return the imageUrls
     */
    public List<String> getImageUrls() {
        return imageUrls;
    }

    /**
     * @param imageUrls the imageUrls to set
     */
    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

}
