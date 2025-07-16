package com.diorama.shop.mapper;

import com.diorama.shop.dto.client.request.OrderItemRequestDTO;
import com.diorama.shop.dto.client.request.OrderRequestDTO;
import com.diorama.shop.dto.client.response.OrderItemResponseDTO;
import com.diorama.shop.dto.client.response.OrderResponseDTO;
import com.diorama.shop.model.Order;
import com.diorama.shop.model.OrderItem;
import com.diorama.shop.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    // Convert Order entity -> OrderResponseDTO
    public static OrderResponseDTO toResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus().name());
        dto.setCustomerNote(order.getCustomerNote());
        dto.setSubtotalAmount(order.getSubtotalAmount());
        dto.setShippingFee(order.getShippingFee());
        dto.setDiscountAmount(order.getDiscountAmount());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setPaymentStatus(order.getPaymentStatus() != null ? order.getPaymentStatus().name() : null);
        dto.setShippingMethod(order.getShippingMethod());
        dto.setTrackingNumber(order.getTrackingNumber());

        List<OrderItemResponseDTO> itemDTOs = order.getOrderItems().stream()
            .map(OrderMapper::toItemResponseDTO)
            .collect(Collectors.toList());

        dto.setItems(itemDTOs);
        return dto;
    }

    // Convert OrderItem -> OrderItemResponseDTO
    public static OrderItemResponseDTO toItemResponseDTO(OrderItem item) {
        OrderItemResponseDTO dto = new OrderItemResponseDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName()); // cần đảm bảo Product có getName()
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    // Convert OrderItemRequestDTO -> OrderItem (Product được truyền vào)
    public static OrderItem toOrderItem(OrderItemRequestDTO dto, Product product) {
        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        item.setPrice(product.getPrice()); // lấy giá từ product tại thời điểm tạo đơn
        return item;
    }

    // Optional: Nếu sau này muốn build lại Order từ RequestDTO (tạm thời ít dùng)
    public static List<OrderItem> toOrderItems(List<OrderItemRequestDTO> itemDTOs, List<Product> products) {
        return itemDTOs.stream().map(dto -> {
            Product product = products.stream()
                    .filter(p -> p.getId().equals(dto.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found: " + dto.getProductId()));
            return toOrderItem(dto, product);
        }).collect(Collectors.toList());
    }
}
