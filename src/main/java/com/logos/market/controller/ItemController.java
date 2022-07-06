package com.logos.market.controller;

import com.logos.market.domain.Item;
import com.logos.market.dto.request.ItemRequestDTO;
import com.logos.market.dto.request.ItemSearchRequestDTO;
import com.logos.market.dto.request.PaginationRequestDTO;
import com.logos.market.dto.response.ItemResponseDTO;
import com.logos.market.dto.response.PageResponseDTO;
import com.logos.market.service.ServiceInt.ItemService;
import com.logos.market.tools.FileTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private FileTool fileTool;

	@PostMapping
	private void createItem(@RequestBody ItemRequestDTO item) throws IOException {
		itemService.save(item);
	}

	@PutMapping("/{id}")
	private ItemResponseDTO updateItem(@RequestBody ItemRequestDTO itemRequest, @PathVariable("id") Long id) throws IOException {
		return new ItemResponseDTO(itemService.update(itemRequest, id));
	}

	@GetMapping("/shops/{id}")
	private List<ItemResponseDTO> getAllByShopId(@PathVariable("id") Long id){
		return itemService.getAllByShopId(id)
				.stream()
				.map(ItemResponseDTO::new)
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	private ItemResponseDTO getById(@PathVariable("id") Long id) {
		return new ItemResponseDTO(itemService.getById(id));
	}

	@GetMapping("/getAllItems")
	private List<ItemResponseDTO> getAll() {
		return itemService.getAll()
				.stream()
				.map(ItemResponseDTO::new)
				.collect(Collectors.toList());
	}

	@GetMapping
	private PageResponseDTO<ItemResponseDTO> getAll(@Valid ItemSearchRequestDTO searchRequestDTO) {
		Page<Item> page = itemService.getPageByShopId(searchRequestDTO);
		return new PageResponseDTO<>(
				page.get()
						.map(ItemResponseDTO::new)
						.collect(Collectors.toList()),
				page.getTotalElements(),
				page.getTotalPages()
		);
	}

	@DeleteMapping("/{id}")
	private void deleteItem(@PathVariable("id") Long id) {
		itemService.delete(id);
	}
}
