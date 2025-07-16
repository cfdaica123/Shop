package com.diorama.shop.dto.client.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class VoucherResponseDTO {
    private Long id;
    private String code;
    private String discountType;
    private BigDecimal discountValue;
    private BigDecimal maxDiscountAmount;
    private BigDecimal minOrderValue;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int usageLimit;
    private int currentUsage;

    // Default constructor
    public VoucherResponseDTO() {}

    // Constructor with all fields
    public VoucherResponseDTO(Long id, String code, String discountType, BigDecimal discountValue,
                            BigDecimal maxDiscountAmount, BigDecimal minOrderValue,
                            LocalDateTime startDate, LocalDateTime endDate, int usageLimit, int currentUsage) {
        this.id = id;
        this.code = code;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.maxDiscountAmount = maxDiscountAmount;
        this.minOrderValue = minOrderValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.usageLimit = usageLimit;
        this.currentUsage = currentUsage;
    }

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
     * @return String return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return String return the discountType
     */
    public String getDiscountType() {
        return discountType;
    }

    /**
     * @param discountType the discountType to set
     */
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    /**
     * @return BigDecimal return the discountValue
     */
    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    /**
     * @param discountValue the discountValue to set
     */
    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    /**
     * @return BigDecimal return the maxDiscountAmount
     */
    public BigDecimal getMaxDiscountAmount() {
        return maxDiscountAmount;
    }

    /**
     * @param maxDiscountAmount the maxDiscountAmount to set
     */
    public void setMaxDiscountAmount(BigDecimal maxDiscountAmount) {
        this.maxDiscountAmount = maxDiscountAmount;
    }

    /**
     * @return BigDecimal return the minOrderValue
     */
    public BigDecimal getMinOrderValue() {
        return minOrderValue;
    }

    /**
     * @param minOrderValue the minOrderValue to set
     */
    public void setMinOrderValue(BigDecimal minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    /**
     * @return LocalDateTime return the startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @return LocalDateTime return the endDate
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * @return int return the usageLimit
     */
    public int getUsageLimit() {
        return usageLimit;
    }

    /**
     * @param usageLimit the usageLimit to set
     */
    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

    /**
     * @return int return the currentUsage
     */
    public int getCurrentUsage() {
        return currentUsage;
    }

    /**
     * @param currentUsage the currentUsage to set
     */
    public void setCurrentUsage(int currentUsage) {
        this.currentUsage = currentUsage;
    }

}