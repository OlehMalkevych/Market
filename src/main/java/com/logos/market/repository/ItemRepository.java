package com.logos.market.repository;

import com.logos.market.domain.Item;
import com.logos.market.domain.Shop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {

    List<Item> getAllByShop(Shop shop);

    Page<Item> getAllByShopId(Long id, Pageable pageable);

    boolean existsByShopId(Long id);
}
