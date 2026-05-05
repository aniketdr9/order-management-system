package com.aniket.ordermanagement.service;

import com.aniket.ordermanagement.dto.OrderItemDto;
import com.aniket.ordermanagement.dto.OrderRequestDto;
import com.aniket.ordermanagement.entity.Order;
import com.aniket.ordermanagement.entity.OrderItem;
import com.aniket.ordermanagement.entity.Product;
import com.aniket.ordermanagement.entity.User;
import com.aniket.ordermanagement.enums.OrderStatus;
import com.aniket.ordermanagement.exception.ResourceNotFoundException;
import com.aniket.ordermanagement.repository.OrderRepository;
import com.aniket.ordermanagement.repository.ProductRepository;
import com.aniket.ordermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(OrderRequestDto dto){
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        BigDecimal totalAmount = BigDecimal.ZERO;
        Order order = new Order();

        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDto itemDto: dto.getItems()){
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            //Stock Check
            if(product.getStock() < itemDto.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " +product.getName());
            }

            //Update Stock
            product.setStock(product.getStock() - itemDto.getQuantity());

            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemDto.getQuantity()));

            totalAmount = totalAmount.add(itemTotal);
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.CREATED);

        return orderRepository.save(order);
    }
}
