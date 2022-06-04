package com.logos.market.repository;

import com.logos.market.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {

    @Query("select  case when count(key(items)) > 0 then true else false end " +
            "from Cart c join c.itemsCountMap items " +
            "where c =:cart and key(items).id = :itemId")
    boolean cartContainsItem(
            @Param("cart") Cart cart,
            @Param("itemId") Long item
            );
}
