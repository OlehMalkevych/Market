package com.logos.market.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.logos.market.domain.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemResponseDTO {
    private Long id;

    private String name;

    private Integer price;

    private Integer count;

    private String description;

    private String image;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:MM")
    private LocalDateTime creationDate;

    public ItemResponseDTO(Item item){
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.count = item.getCount();
        this.description = item.getDescription();
        this.creationDate = item.getCreationDate();
        this.image = item.getImage();
    }
}
