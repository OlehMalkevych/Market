package com.logos.market.service.serviceImpl;

import com.logos.market.domain.Category;
import com.logos.market.domain.Shop;
import com.logos.market.dto.request.CategoryRequestDTO;
import com.logos.market.repository.CategoryRepository;
import com.logos.market.service.ServiceInt.CategoryService;
import com.logos.market.service.ServiceInt.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    private ShopService shopService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(CategoryRequestDTO requestDTO) {
        categoryRepository.save(mapCategoryRequestDTOToCategory(requestDTO));
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Category with id " + id + " doesn't exist")
        );
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getAllCategoriesByShopId(Long id) {
//        try{
//            System.out.println(" shop: " + shopService.getById(id));
//        }catch (Exception e){
//            System.out.println("shop");
//            System.out.println(shopService.getById(1L));
//        }
////        try{
////            System.out.println("list" + categoryRepository.getAllByShops(shopService.getById(id)));
////        }catch (Exception e){
////            System.out.println("list");
////        }
//


        return categoryRepository.getAllByShops(shopService.getById(id));
    }

    private Category mapCategoryRequestDTOToCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());

        return category;
    }
}