package com.diorama.shop.controller.admin;

import com.diorama.shop.dto.client.response.OrderResponseDTO;
import com.diorama.shop.model.Enums.OrderStatus;
import com.diorama.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    // Lấy tất cả đơn hàng
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    // Lấy chi tiết một đơn hàng cụ thể
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    // Cập nhật trạng thái đơn hàng (duyệt, giao hàng, hoàn thành, huỷ)
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<OrderResponseDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status
    ) {
        return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    }

    // Xoá đơn hàng khỏi hệ thống (hard delete)
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
