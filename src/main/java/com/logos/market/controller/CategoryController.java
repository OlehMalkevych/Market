package com.logos.market.controller;

import com.logos.market.dto.request.CategoryRequestDTO;
import com.logos.market.service.ServiceInt.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    private void createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        categoryService.create(categoryRequestDTO);
    }
}
