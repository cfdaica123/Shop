package com.diorama.shop.model.Enums;

public enum OrderStatus {
    PENDING,        // Chờ xác nhận
    PROCESSING,     // Đang xử lý
    SHIPPED,        // Đã giao cho đơn vị vận chuyển
    DELIVERED,      // Đã giao thành công
    CANCELLED,      // Đã hủy
    RETURNED        // Đã hoàn trả
}