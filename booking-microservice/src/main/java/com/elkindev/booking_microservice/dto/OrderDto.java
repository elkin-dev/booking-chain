package com.elkindev.booking_microservice.dto;

import com.elkindev.booking_microservice.entity.OrderItemEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    private List<OrderItemEntity> orderItemEntityList;
}
