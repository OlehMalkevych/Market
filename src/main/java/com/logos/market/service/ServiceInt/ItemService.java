package com.logos.market.service.ServiceInt;

import com.logos.market.domain.Item;
import com.logos.market.dto.request.ItemRequestDTO;
import com.logos.market.dto.request.ItemSearchRequestDTO;
import com.logos.market.dto.request.PaginationRequestDTO;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface ItemService {
    void save(ItemRequestDTO item);

    Item getById(Long id);

    List<Item> getAll();

    Page<Item> getPageByShopId(@NotNull ItemSearchRequestDTO itemSearchRequestDTO);

    Item update(ItemRequestDTO item, Long id);

    void delete(Long id);
}
