package com.logos.market.service.serviceImpl;

import com.logos.market.domain.Category;
import com.logos.market.domain.Shop;
import com.logos.market.dto.request.PaginationRequestDTO;
import com.logos.market.dto.request.ShopRequestDTO;
import com.logos.market.repository.ShopRepository;
import com.logos.market.service.ServiceInt.CategoryService;
import com.logos.market.service.ServiceInt.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        Shop savedShop = shopRepository.save(mapShopRequestDTOToShop(shopRequestDTO, null));
        return shopRepository.save(addCategoriesToShop(savedShop, shopRequestDTO));
    }

    @Override
    public Shop update(ShopRequestDTO shopRequestDTO, Long id) {
        Shop shop = mapShopRequestDTOToShop(shopRequestDTO, getById(id));
        return shopRepository.save(addCategoriesToShop(shop, shopRequestDTO));
    }

    @Override
    public Shop getById(Long id) {
        return shopRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }


    @Override
    public List<Shop> getAllByCategory(Long categoryId) {
        return shopRepository.findAllByCategory(categoryId);
    }

    @Override
    public Page<Shop> getPage(PaginationRequestDTO paginationRequestDTO) {
        return shopRepository.findAll(paginationRequestDTO.mapToPageable());
    }

    @Override
    public void delete(Long id) {
        shopRepository.deleteById(id);
    }

    private Shop addCategoriesToShop(Shop shop, ShopRequestDTO requestDTO){
        if (requestDTO.getCategoryIds() != null) {
            requestDTO.getCategoryIds().forEach((id) -> {
                Category category = categoryService.getById(id);
                shop.getCategories().add(category);
                category.getShops().add(shop);
            });
        }
        return shop;
    }

    private Shop mapShopRequestDTOToShop(ShopRequestDTO shopRequestDTO, Shop shop) {
        if (shop == null) {
            shop = new Shop();
        }
        shop.setName(shopRequestDTO.getName());
        return shop;
    }
}
