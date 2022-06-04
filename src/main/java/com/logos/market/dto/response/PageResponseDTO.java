package com.logos.market.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageResponseDTO<T> {

    private List<T> data;
    private Long totalElements;
    private Integer totalPages;
}
