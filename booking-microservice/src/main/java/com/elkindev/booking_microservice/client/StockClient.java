package com.elkindev.booking_microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "stock-microservice",
        url = "/api/stock")
public interface StockClient {

    @GetMapping("/{code}")
    boolean stockAvailable(@PathVariable String code);
}
