package com.logos.market.service.ServiceInt;


import com.logos.market.domain.Category;
import com.logos.market.domain.Shop;
import com.logos.market.dto.request.PaginationRequestDTO;
import com.logos.market.dto.request.ShopRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShopService {

    Shop save(ShopRequestDTO shop);

    Shop update(ShopRequestDTO shop, Long id);

    Shop getById(Long id);

    List<Shop> getAll();

    List<Shop> getAllByCategory(Long categoryId);

    Page<Shop> getPage(PaginationRequestDTO paginationRequestDTO);

    void delete(Long id);
}
