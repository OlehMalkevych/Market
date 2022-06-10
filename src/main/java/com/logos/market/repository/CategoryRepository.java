package com.logos.market.repository;

import com.logos.market.domain.Category;
import com.logos.market.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
//    @Query(value = "select cs.* from category_shop cs" +
//            "    inner join _shop s on cs.shop_id = s.id ", nativeQuery = true)

//    List<Category> getAllByShopCategory(Shop shop);

    List<Category> getAllByShops(Shop shops);



}
