package com.logos.market.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemSearchRequestDTO {
    @NotNull
    private Integer shopId;
    private Integer maxPrice;
    private Integer minPrice;
    private String name;
    private Integer count;

    @JsonProperty("pagination")
    private PaginationRequestDTO pagination;

}
