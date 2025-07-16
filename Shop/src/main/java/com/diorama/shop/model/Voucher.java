package com.diorama.shop.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code; // Ví dụ: "DIORAMA10"

    // Có thể dùng Enum: PERCENT, FIXED_AMOUNT
    @Column(name = "discount_type", nullable = false)
    private String discountType;

    @Column(name = "discount_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal discountValue;

    @Column(name = "max_discount_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal maxDiscountAmount; // Mức giảm tối đa (cho loại PERCENT)

    @Column(name = "min_order_value", nullable = false, precision = 15, scale = 2)
    private BigDecimal minOrderValue; // Giá trị đơn hàng tối thiểu để áp dụng

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "usage_limit")
    private int usageLimit; // Tổng số lần có thể sử dụng

    @Column(name = "current_usage", columnDefinition = "INT DEFAULT 0")
    private int currentUsage; // Số lần đã sử dụng

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