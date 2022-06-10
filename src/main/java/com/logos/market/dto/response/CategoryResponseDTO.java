package com.logos.market.dto.response;

import com.logos.market.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDTO {

	private Long id;
	private String name;

	public CategoryResponseDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}
}
