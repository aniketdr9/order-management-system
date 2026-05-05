package com.aniket.ordermanagement.controller;

import com.aniket.ordermanagement.dto.OrderRequestDto;
import com.aniket.ordermanagement.entity.Order;
import com.aniket.ordermanagement.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody OrderRequestDto request){
        return orderService.createOrder(request);
    }
}
