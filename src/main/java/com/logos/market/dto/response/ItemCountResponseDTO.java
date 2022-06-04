package com.logos.market.dto.response;

import com.logos.market.domain.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCountResponseDTO {

    private ItemResponseDTO item;

    private Integer count;

    public ItemCountResponseDTO(Item item, Integer count) {
        this.item = new ItemResponseDTO(item);
        this.count = count;
    }
}
