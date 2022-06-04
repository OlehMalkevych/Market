package com.logos.market.controller;

import com.logos.market.dto.response.CartResponseDTO;
import com.logos.market.service.ServiceInt.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@CrossOrigin
public class CartController {
    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/add-item")
    private void addItemToCart(Long id, Long itemId){
        cartService.addItem(id, itemId);
    }

    @GetMapping
    private CartResponseDTO getCart(Long id){
        return new CartResponseDTO(cartService.getById(id));
    }

}
