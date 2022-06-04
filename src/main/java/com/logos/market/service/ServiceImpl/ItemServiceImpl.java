package com.logos.market.service.ServiceImpl;

import com.logos.market.domain.Item;
import com.logos.market.dto.request.ItemRequestDTO;
import com.logos.market.dto.request.ItemSearchRequestDTO;
import com.logos.market.repository.ItemRepository;
import com.logos.market.service.ServiceInt.ItemService;
import com.logos.market.service.ServiceInt.ShopService;
import com.logos.market.specification.ItemSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopService shopService;

    @Override
    public void save(ItemRequestDTO itemRequest) {
        itemRepository.save(mapItemRequestToItem(itemRequest, null));
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item with id " + id + "doesn't exist"));
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Page<Item> getPageByShopId(ItemSearchRequestDTO searchRequestDTO) {
        return itemRepository
                .findAll(new ItemSpecification(searchRequestDTO),
                        searchRequestDTO.getPagination().mapToPageable());
    }

    @Override
    public Item update(ItemRequestDTO itemRequest, Long id) {
        return itemRepository.save(mapItemRequestToItem(itemRequest, getById(id)));
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
    private Item mapItemRequestToItem(ItemRequestDTO itemRequest, Item item){
        if (item == null){
            item = new Item();
        }
        item.setName(itemRequest.getName());
        item.setPrice(itemRequest.getPrice());
        item.setCount(itemRequest.getCount());
        item.setDescription(itemRequest.getDescription());
        item.setShop(shopService.getById(itemRequest.getShopId()));

        return item;
    }
}


