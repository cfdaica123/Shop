package com.diorama.shop.service;

import com.diorama.shop.dto.client.request.OrderRequestDTO;
import com.diorama.shop.dto.client.response.OrderResponseDTO;
import com.diorama.shop.mapper.OrderMapper;
import com.diorama.shop.model.*;
import com.diorama.shop.model.Enums.OrderStatus;
import com.diorama.shop.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AddressRepository addressRepository;
    @Autowired private VoucherRepository voucherRepository;
    @Autowired private ProductRepository productRepository;

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(OrderMapper::toResponseDTO).toList();
    }

    public List<OrderResponseDTO> getMyOrders() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return orderRepository.findByUser(user).stream().map(OrderMapper::toResponseDTO).toList();
    }

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        Address address = addressRepository.findById(dto.getShippingAddressId())
                .orElseThrow(() -> new IllegalStateException("Address not found"));

        Voucher voucher = null;
        if (dto.getVoucherId() != null) {
            voucher = voucherRepository.findById(dto.getVoucherId())
                    .filter(v -> v.getStartDate().isBefore(LocalDateTime.now()) &&
                                 v.getEndDate().isAfter(LocalDateTime.now()) &&
                                 v.getCurrentUsage() < v.getUsageLimit())
                    .orElseThrow(() -> new IllegalStateException("Invalid or expired voucher"));
        }

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(address);
        order.setVoucher(voucher);
        order.setCustomerNote(dto.getCustomerNote());
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setShippingMethod(dto.getShippingMethod());
        order.setStatus(OrderStatus.PENDING);

        BigDecimal[] subtotalRef = {BigDecimal.ZERO};
        List<OrderItem> items = dto.getItems().stream().map(itemDTO -> {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new IllegalStateException("Product not found"));
            BigDecimal price = product.getPrice();
            BigDecimal itemTotal = price.multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            subtotalRef[0] = subtotalRef[0].add(itemTotal);

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(price);
            item.setOrder(order);
            return item;
        }).toList();

        order.setOrderItems(items);
        order.setSubtotalAmount(subtotalRef[0]);

        BigDecimal shippingFee = new BigDecimal("30000");
        order.setShippingFee(shippingFee);

        BigDecimal discount = BigDecimal.ZERO;
        if (voucher != null && subtotalRef[0].compareTo(voucher.getMinOrderValue()) >= 0) {
            if ("PERCENT".equalsIgnoreCase(voucher.getDiscountType())) {
                discount = subtotalRef[0].multiply(voucher.getDiscountValue())
                        .divide(BigDecimal.valueOf(100));
                if (discount.compareTo(voucher.getMaxDiscountAmount()) > 0) {
                    discount = voucher.getMaxDiscountAmount();
                }
            } else if ("FIXED_AMOUNT".equalsIgnoreCase(voucher.getDiscountType())) {
                discount = voucher.getDiscountValue();
            }
            voucher.setCurrentUsage(voucher.getCurrentUsage() + 1);
            voucherRepository.save(voucher);
        }
        order.setDiscountAmount(discount);

        BigDecimal total = subtotalRef[0].add(shippingFee).subtract(discount);
        order.setTotalAmount(total);

        orderRepository.save(order);
        return OrderMapper.toResponseDTO(order);
    }

    public OrderResponseDTO cancelOrderByUser(Long orderId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("You don't have permission to cancel this order");
        }

        if (!order.getStatus().equals(OrderStatus.PENDING)) {
            throw new IllegalStateException("Cannot cancel order in current status");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
        return OrderMapper.toResponseDTO(order);
    }

    public OrderResponseDTO getOrderDetailForUser(Long orderId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("You don't have permission to view this order");
        }

        return OrderMapper.toResponseDTO(order);
    }

    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found"));
        return OrderMapper.toResponseDTO(order);
    }

    public OrderResponseDTO updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalStateException("Order not found"));

        if (order.getStatus().equals(OrderStatus.CANCELLED) || order.getStatus().equals(OrderStatus.DELIVERED)) {
            throw new IllegalStateException("Cannot update order with current status");
        }

        order.setStatus(status);
        orderRepository.save(order);
        return OrderMapper.toResponseDTO(order);
    }

    public void deleteOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new IllegalStateException("Order not found");
        }
        orderRepository.deleteById(orderId);
    }
}
