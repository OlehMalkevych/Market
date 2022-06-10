package com.logos.market.controller;

import com.logos.market.dto.request.CategoryRequestDTO;
import com.logos.market.dto.response.CategoryResponseDTO;
import com.logos.market.service.ServiceInt.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    private void createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryService.create(categoryRequestDTO);
    }

    @GetMapping
    private List<CategoryResponseDTO> getAll() {
        return categoryService.getAll()
                .stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{shopId}")
    private List<CategoryResponseDTO> getAllCategoriesByShopId(@PathVariable("shopId") Long shopId){
        return categoryService.getAllCategoriesByShopId(shopId)
                .stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
    }
}

