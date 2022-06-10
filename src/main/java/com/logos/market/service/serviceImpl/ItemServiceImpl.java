package com.logos.market.service.serviceImpl;

import com.logos.market.domain.Item;
import com.logos.market.dto.request.ItemRequestDTO;
import com.logos.market.dto.request.ItemSearchRequestDTO;
import com.logos.market.repository.ItemRepository;
import com.logos.market.service.ServiceInt.ItemService;
import com.logos.market.service.ServiceInt.ShopService;
import com.logos.market.specification.ItemSpecification;
import com.logos.market.tools.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopService shopService;

    @Autowired
    private FileTool fileTool;

    @Override
    public void save(ItemRequestDTO itemRequest) throws IOException {
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
    public List<Item> getAllByShopId(Long id) {
        return itemRepository.getAllByShop(shopService.getById(id));
    }

    @Override
    public Page<Item> getPageByShopId(ItemSearchRequestDTO searchRequestDTO) {
        return itemRepository
                .findAll(new ItemSpecification(searchRequestDTO),
                        searchRequestDTO.getPagination().mapToPageable());
    }

    @Override
    public Item update(ItemRequestDTO itemRequest, Long id) throws IOException {
        return itemRepository.save(mapItemRequestToItem(itemRequest, getById(id)));
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
    private Item mapItemRequestToItem(ItemRequestDTO itemRequestDTO, Item item) throws IOException {
        if (item == null){
            item = new Item();
        }
        item.setName(itemRequestDTO.getName());
        item.setPrice(itemRequestDTO.getPrice());
        item.setCount(itemRequestDTO.getCount());
        item.setDescription(itemRequestDTO.getDescription());
        item.setShop(shopService.getById(itemRequestDTO.getShopId()));
        if (itemRequestDTO.getImage() != null){
            item.setImage(fileTool.saveFile(itemRequestDTO.getImage()));
        }
        return item;
    }
}


