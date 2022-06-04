package com.logos.market.dto.response;

import com.logos.market.domain.Shop;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShopResponseDTO {

    private Long id;

    private String name;

    public ShopResponseDTO(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
    }
}
