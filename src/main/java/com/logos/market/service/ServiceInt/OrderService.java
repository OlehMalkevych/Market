package com.logos.market.service.ServiceInt;

import com.logos.market.domain.Cart;
import com.logos.market.domain.Item;
import com.logos.market.domain.Order;

public interface OrderService {

    void addItem(Long orderId, Long itemId);

    Order getById(Long id);

}
