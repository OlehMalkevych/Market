package com.logos.market.dto.request;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ShopRequestDTO {

    private String name;

    private Set<Long> categoryIds = new HashSet<>();


}
