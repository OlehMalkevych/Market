package com.logos.market.service.ServiceImpl;

import com.logos.market.domain.Category;
import com.logos.market.dto.request.CategoryRequestDTO;
import com.logos.market.repository.CategoryRepository;
import com.logos.market.service.ServiceInt.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(CategoryRequestDTO requestDTO) {
        categoryRepository.save(mapCategoryRequestDTOToCategory(requestDTO));
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category with id " + id + "doesn't exist"));
    }

    private Category mapCategoryRequestDTOToCategory(CategoryRequestDTO categoryRequestDTO){
        Category category = new Category();

        category.setName(categoryRequestDTO.getName());
        return category;
    }
}
