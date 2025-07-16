package com.diorama.shop.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.diorama.shop.model.Enums.OrderStatus;
import com.diorama.shop.model.Enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- CÁC LIÊN KẾT KHÓA NGOẠI ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id") // Có thể null nếu không áp dụng voucher
    private Voucher voucher;

    // --- THÔNG TIN CHUNG VỀ ĐƠN HÀNG ---
    @CreationTimestamp
    @Column(name = "order_date", updatable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) // Map sang VARCHAR trong DB
    @Column(nullable = false)
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Lob
    @Column(name = "customer_note")
    private String customerNote;

    // --- CHI TIẾT TÀI CHÍNH ---
    @Column(name = "subtotal_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotalAmount;

    @Column(name = "shipping_fee", nullable = false, precision = 15, scale = 2)
    private BigDecimal shippingFee;

    @Column(name = "discount_amount", precision = 15, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    // --- CHI TIẾT THANH TOÁN & VẬN CHUYỂN ---
    @Column(name = "payment_method")
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

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
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return Address return the shippingAddress
     */
    public Address getShippingAddress() {
        return shippingAddress;
    }

    /**
     * @param shippingAddress the shippingAddress to set
     */
    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    /**
     * @return Voucher return the voucher
     */
    public Voucher getVoucher() {
        return voucher;
    }

    /**
     * @param voucher the voucher to set
     */
    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
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
     * @return OrderStatus return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * @return List<OrderItem> return the orderItems
     */
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
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
     * @return PaymentStatus return the paymentStatus
     */
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @param paymentStatus the paymentStatus to set
     */
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * @return String return the transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

}