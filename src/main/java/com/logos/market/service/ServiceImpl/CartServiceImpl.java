package com.logos.market.service.ServiceImpl;

import com.logos.market.domain.Cart;
import com.logos.market.domain.Item;
import com.logos.market.repository.CartRepository;
import com.logos.market.service.ServiceInt.CartService;
import com.logos.market.service.ServiceInt.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    private ItemService itemService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ItemService itemService) {
        this.cartRepository = cartRepository;
        this.itemService = itemService;
    }

    @Override
    public void addItem(Long cartId, Long itemId) {
        Cart cart = getById(cartId);
        Item item = itemService.getById(itemId);
        cart.getItemsCountMap().putIfAbsent(item,1);

        cartRepository.save(cart);
    }

    @Override
    public Cart getById(Long id){
        return cartRepository.getById(id);
    }
}
