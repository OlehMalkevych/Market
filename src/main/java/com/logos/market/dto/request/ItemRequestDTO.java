package com.logos.market.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemRequestDTO {

    private String name;

    private Integer price;

    private Integer count;

    private String description;

    private Long ShopId;

}
