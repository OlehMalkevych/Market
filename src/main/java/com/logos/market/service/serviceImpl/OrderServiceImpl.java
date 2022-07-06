package com.logos.market.service.serviceImpl;

import com.logos.market.domain.Item;
import com.logos.market.domain.Order;
import com.logos.market.repository.OrderRepository;
import com.logos.market.service.ServiceInt.ItemService;
import com.logos.market.service.ServiceInt.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private ItemService itemService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    @Override
    public void addItem(Long orderId, Long itemId) {
        Order order = getById(orderId);
        Item item = itemService.getById(itemId);
        order.getItemsCountMap().putIfAbsent(item,1);

        orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return null;
    }
}
