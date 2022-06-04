package com.logos.market.service.ServiceInt;


import com.logos.market.domain.Shop;
import com.logos.market.dto.request.ShopRequestDTO;

import java.util.List;

public interface ShopService {

    Shop save(ShopRequestDTO shop);

    Shop getById(Long id);

    List<Shop> getAllByCategory(Long categoryId);
}
