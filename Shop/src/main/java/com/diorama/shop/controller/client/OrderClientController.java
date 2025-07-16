package com.diorama.shop.controller.client;

import com.diorama.shop.dto.client.request.OrderRequestDTO;
import com.diorama.shop.dto.client.response.OrderResponseDTO;
import com.diorama.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderClientController {

    @Autowired
    private OrderService orderService;

    // Lấy danh sách đơn hàng của người dùng hiện tại
    @GetMapping
    public List<OrderResponseDTO> getMyOrders() {
        return orderService.getMyOrders();
    }

    // Tạo mới đơn hàng
    @PostMapping
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO dto) {
        return orderService.createOrder(dto);
    }

    // Huỷ đơn hàng của chính mình
    @PutMapping("/{id}/cancel")
    public OrderResponseDTO cancelMyOrder(@PathVariable Long id) {
        return orderService.cancelOrderByUser(id);
    }

    // Xem chi tiết đơn hàng của mình
    @GetMapping("/{id}")
    public OrderResponseDTO getOrderDetail(@PathVariable Long id) {
        return orderService.getOrderDetailForUser(id);
    }
}