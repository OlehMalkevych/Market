package com.logos.market.dto.response;

import com.logos.market.domain.Cart;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
public class CartResponseDTO {

    private Integer sum = 0;

    private List<ItemCountResponseDTO> items = new ArrayList<>();

    public CartResponseDTO(Cart cart) {
        items = cart.getItemsCountMap()
                .entrySet()
                .stream()
                .peek(e -> sum += e.getKey().getPrice() * e.getValue())
                .map(e -> new ItemCountResponseDTO(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
