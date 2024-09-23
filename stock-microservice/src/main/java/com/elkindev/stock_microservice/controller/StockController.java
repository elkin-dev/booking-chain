package com.elkindev.stock_microservice.controller;

import com.elkindev.stock_microservice.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final StockRepository stockRepository;

    @Autowired
    public StockController(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @GetMapping("/{code}")
    public boolean stockAvailable(@PathVariable String code) {
        var stock = this.stockRepository.findByCode(code);
        stock.orElseThrow(() -> new RuntimeException("Can not find the product " + code));
        return stock.get().getQuantity() > 0;
    }
}
