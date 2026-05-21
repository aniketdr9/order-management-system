package com.aniket.ordermanagement.controller;

import com.aniket.ordermanagement.dto.ApiResponse;
import com.aniket.ordermanagement.dto.OrderRequestDto;
import com.aniket.ordermanagement.dto.OrderResponseDto;
import com.aniket.ordermanagement.entity.Order;
import com.aniket.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequestDto request){
        return orderService.createOrder(request);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<OrderResponseDto>>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(
                ApiResponse.<Page<OrderResponseDto>>builder()
                        .success(true)
                        .message("Orders fetched successfully")
                        .data(orderService.getAllOrders(page, size))
                        .build()

        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(
                ApiResponse.<OrderResponseDto>builder()
                        .success(true)
                        .message("Order fetched successfully")
                        .data(orderService.getOrderById(id))
                        .build()
        );
    }
}
