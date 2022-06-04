package com.logos.market.repository;

import com.logos.market.domain.Category;
import com.logos.market.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query(value = "select s.* from _shop s" +
            " inner join category_shop cs on cs.shop_id = s.id" +
            " where cs.category_id = :id", nativeQuery = true)
    List<Shop> findAllByCategory(@Param("id") Long id);
}
