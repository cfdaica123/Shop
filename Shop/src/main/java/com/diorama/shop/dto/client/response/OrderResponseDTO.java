package com.diorama.shop.dto.client.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    private Long id;
    private LocalDateTime orderDate;
    private String status;
    private String customerNote;
    private BigDecimal subtotalAmount;
    private BigDecimal shippingFee;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String shippingMethod;
    private String trackingNumber;
    private List<OrderItemResponseDTO> items;

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
     * @return LocalDateTime return the orderDate
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return String return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return String return the customerNote
     */
    public String getCustomerNote() {
        return customerNote;
    }

    /**
     * @param customerNote the customerNote to set
     */
    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    /**
     * @return BigDecimal return the subtotalAmount
     */
    public BigDecimal getSubtotalAmount() {
        return subtotalAmount;
    }

    /**
     * @param subtotalAmount the subtotalAmount to set
     */
    public void setSubtotalAmount(BigDecimal subtotalAmount) {
        this.subtotalAmount = subtotalAmount;
    }

    /**
     * @return BigDecimal return the shippingFee
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    /**
     * @param shippingFee the shippingFee to set
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * @return BigDecimal return the discountAmount
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * @param discountAmount the discountAmount to set
     */
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * @return BigDecimal return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return String return the paymentMethod
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * @param paymentMethod the paymentMethod to set
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * @return String return the paymentStatus
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return String return the shippingMethod
     */
    public String getShippingMethod() {
        return shippingMethod;
    }

    /**
     * @param shippingMethod the shippingMethod to set
     */
    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    /**
     * @return String return the trackingNumber
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * @param trackingNumber the trackingNumber to set
     */
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    /**
     * @return List<OrderItemResponseDTO> return the items
     */
    public List<OrderItemResponseDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<OrderItemResponseDTO> items) {
        this.items = items;
    }

}

