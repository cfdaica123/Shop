package com.diorama.shop.dto.client.request;

import java.util.List;

public class OrderRequestDTO {
    private Long shippingAddressId;
    private Long voucherId;
    private String customerNote;
    private String paymentMethod;
    private String shippingMethod;
    private List<OrderItemRequestDTO> items;

    /**
     * @return Long return the shippingAddressId
     */
    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    /**
     * @param shippingAddressId the shippingAddressId to set
     */
    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    /**
     * @return Long return the voucherId
     */
    public Long getVoucherId() {
        return voucherId;
    }

    /**
     * @param voucherId the voucherId to set
     */
    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
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
     * @return List<OrderItemRequestDTO> return the items
     */
    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }

}

