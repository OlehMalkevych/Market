package com.logos.market.controller;


import com.logos.market.dto.request.ShopRequestDTO;
import com.logos.market.dto.response.ShopResponseDTO;
import com.logos.market.service.ServiceInt.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shops")
@CrossOrigin
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping
    private ShopResponseDTO create(@RequestBody ShopRequestDTO shopRequestDTO){
        return new ShopResponseDTO(shopService.save(shopRequestDTO));
    }

    @GetMapping("/{id}")
    private ShopResponseDTO get(@PathVariable("id") Long id){
        return new ShopResponseDTO(shopService.getById(id));
    }
    @PutMapping("/{id}")
    private ShopResponseDTO update(@RequestBody ShopRequestDTO shop, @PathVariable("id") Long id) {
        return new ShopResponseDTO(shopService.update(shop, id));
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Long id) {
        shopService.delete(id);
    }

    @GetMapping()
    private List<ShopResponseDTO> getAll() {
        return shopService.getAll()
                .stream()
                .map(ShopResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryId}")
    private List<ShopResponseDTO> getAllByCategory(@PathVariable("categoryId") Long id){
        return shopService.getAllByCategory(id)
                .stream()
                .map(ShopResponseDTO::new)
                .collect(Collectors.toList());
    }
}
