package com.elkindev.booking_microservice.controller;

import com.elkindev.booking_microservice.dto.OrderDto;
import com.elkindev.booking_microservice.entity.OrderEntity;
import com.elkindev.booking_microservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final OrderRepository orderRepository;

    @Autowired
    public BookingController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/order")
    public String saveOrder(@RequestBody OrderDto orderDto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(UUID.randomUUID().toString());
        orderEntity.setOrderItemEntityList(orderDto.getOrderItemEntityList());

        this.orderRepository.save(orderEntity);
        
        return "order saved";
    }
}
