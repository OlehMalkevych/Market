package com.logos.market.service.ServiceInt;

import com.logos.market.domain.Category;
import com.logos.market.dto.request.CategoryRequestDTO;

import java.util.List;

public interface CategoryService {
    void create(CategoryRequestDTO requestDTO);

    Category getById(Long id);

    List<Category> getAll();

    List<Category> getAllCategoriesByShopId(Long id);
}
