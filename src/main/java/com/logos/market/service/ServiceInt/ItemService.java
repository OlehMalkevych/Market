package com.logos.market.service.ServiceInt;

import com.logos.market.domain.Item;
import com.logos.market.dto.request.ItemRequestDTO;
import com.logos.market.dto.request.ItemSearchRequestDTO;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

public interface ItemService {
    void save(ItemRequestDTO item) throws IOException;

    Item getById(Long id);

    List<Item> getAll();

    List<Item> getAllByShopId(Long id);

    Page<Item> getPageByShopId(@NotNull ItemSearchRequestDTO itemSearchRequestDTO);

    Item update(ItemRequestDTO item, Long id) throws IOException;

    void delete(Long id);
}
