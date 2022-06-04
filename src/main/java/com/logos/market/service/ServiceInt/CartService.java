package com.logos.market.service.ServiceInt;

import com.logos.market.domain.Cart;

public interface CartService {

    void addItem(Long cartId, Long itemId);

    Cart getById(Long id);
}
