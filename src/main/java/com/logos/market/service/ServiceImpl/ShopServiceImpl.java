package com.logos.market.service.ServiceImpl;

import com.logos.market.domain.Category;
import com.logos.market.domain.Shop;
import com.logos.market.dto.request.ShopRequestDTO;
import com.logos.market.repository.ShopRepository;
import com.logos.market.service.ServiceInt.CategoryService;
import com.logos.market.service.ServiceInt.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepository shopRepository;
    @Autowired
    private CategoryService categoryService;

    @Override
    public Shop save(ShopRequestDTO shopRequestDTO) {
        Shop savedShop = shopRepository.save(mapShopRequestDTOToShop(shopRequestDTO));
        return shopRepository.save(addCategoryToShop(savedShop, shopRequestDTO));
    }

    @Override
    public Shop getById(Long id) {
        return shopRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public List<Shop> getAllByCategory(Long categoryId) {
        return shopRepository.findAllByCategory(categoryId);
    }

    private Shop addCategoryToShop(Shop shop, ShopRequestDTO requestDTO){
        if (requestDTO.getCategoryIds() != null) {
            requestDTO.getCategoryIds().forEach((id) -> {
                Category category = categoryService.getById(id);
                shop.getCategories().add(category);
                category.getShops().add(shop);
            });
        }
        return shop;
    }

    private Shop mapShopRequestDTOToShop(ShopRequestDTO shopRequestDTO){
        Shop shop = new Shop();
        shop.setName(shopRequestDTO.getName());
        if (shopRequestDTO.getCategoryIds() != null) {
            shopRequestDTO.getCategoryIds().forEach(
                    (id) -> {shop.getCategories().add(categoryService.getById(id));

            });
        }

        return shop;

    }
}
