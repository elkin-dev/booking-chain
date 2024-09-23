package com.elkindev.booking_microservice.controller;

import com.elkindev.booking_microservice.client.StockClient;
import com.elkindev.booking_microservice.dto.OrderDto;
import com.elkindev.booking_microservice.entity.OrderEntity;
import com.elkindev.booking_microservice.repository.OrderRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    private final StockClient stockClient;

    @Autowired
    public BookingController(OrderRepository orderRepository, StockClient stockClient) {
        this.orderRepository = orderRepository;
        this.stockClient = stockClient;
    }

    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDto orderDto) {

        boolean inStock = orderDto.getOrderItemEntityList().stream()
                .allMatch(orderItemEntity -> stockClient.stockAvailable(orderItemEntity.getCode()));

        if (inStock) {

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderNo(UUID.randomUUID().toString());
            orderEntity.setOrderItemEntityList(orderDto.getOrderItemEntityList());

            this.orderRepository.save(orderEntity);

            return "order saved";
        }
        return "Order cannot by saved";
    }

    private String fallbackToStockService() {
        return "Something went wrong, please try after sometime";
    }
}
