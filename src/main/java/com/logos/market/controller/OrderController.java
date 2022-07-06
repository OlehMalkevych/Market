package com.logos.market.controller;

import com.logos.market.dto.response.CartResponseDTO;
import com.logos.market.service.ServiceInt.CartService;
import com.logos.market.service.ServiceInt.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/add-item")
    private void addItemToCart(Long id, Long itemId){
        orderService.addItem(id, itemId);
    }

}

