package com.diorama.shop.dto.client.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VoucherRequestDTO {
    
    @NotBlank(message = "Code is required")
    private String code;
    
    @NotBlank(message = "Discount type is required")
    private String discountType;
    
    @NotNull(message = "Discount value is required")
    @Positive(message = "Discount value must be positive")
    private BigDecimal discountValue;
    
    private BigDecimal maxDiscountAmount;
    
    private BigDecimal minOrderValue;
    
    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;
    
    @NotNull(message = "End date is required")
    private LocalDateTime endDate;
    
    @Positive(message = "Usage limit must be positive")
    private Integer usageLimit;
    
    // Default constructor
    public VoucherRequestDTO() {}
    
    // Constructor with all fields
    public VoucherRequestDTO(String code, String discountType, BigDecimal discountValue,
                           BigDecimal maxDiscountAmount, BigDecimal minOrderValue,
                           LocalDateTime startDate, LocalDateTime endDate, Integer usageLimit) {
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.maxDiscountAmount = maxDiscountAmount;
        this.minOrderValue = minOrderValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usageLimit = usageLimit;
    }
    
    // Getters and Setters
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getDiscountType() {
        return discountType;
    }
    
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
    
    public BigDecimal getDiscountValue() {
        return discountValue;
    }
    
    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }
    
    public BigDecimal getMaxDiscountAmount() {
        return maxDiscountAmount;
    }
    
    public void setMaxDiscountAmount(BigDecimal maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }
    
    public BigDecimal getMinOrderValue() {
        return minOrderValue;
    }
    
    public void setMinOrderValue(BigDecimal minOrderValue) {
        this.minOrderValue = minOrderValue;
    }
    
    public LocalDateTime getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    
    public LocalDateTime getEndDate() {
        return endDate;
    }
    
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
    
    public Integer getUsageLimit() {
        return usageLimit;
    }
    
    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }
}
