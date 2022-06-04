package com.logos.market.specification;

import com.logos.market.domain.Item;
import com.logos.market.domain.Shop;
import com.logos.market.dto.request.ItemSearchRequestDTO;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ItemSpecification implements Specification<Item> {

    private Integer shopId;
    private String name;
    private Integer maxPrice;
    private Integer minPrice;
    private Integer count;

    public ItemSpecification(ItemSearchRequestDTO itemSearchRequestDTO) {
        this.name = itemSearchRequestDTO.getName();
        this.count = itemSearchRequestDTO.getCount();
        this.minPrice = itemSearchRequestDTO.getMinPrice();
        this.maxPrice = itemSearchRequestDTO.getMaxPrice();
        this.shopId = itemSearchRequestDTO.getShopId();
    }

    @Override
    public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(findByPrice(root,cb));
        predicates.add(findByCount(root,cb));
        predicates.add(findByName(root,cb));
        predicates.add(findByShop(root, cb));
        return cb.and(predicates.toArray(new Predicate[0]));

    }
    private Predicate findByPrice(Root<Item> root, CriteriaBuilder cb){
        Predicate predicate;

        if (minPrice == null && maxPrice == null){
            predicate = cb.conjunction();
        } else if(minPrice == null) {
            predicate = cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        } else if(maxPrice == null){
            predicate = cb.greaterThanOrEqualTo(root.get("price"), minPrice);
        } else {
            predicate = cb.between(root.get("price"), minPrice, maxPrice);
        }

        return predicate;
    }

    private Predicate findByCount(Root<Item> root, CriteriaBuilder cb){
        Predicate predicate;

        if (count == null) {
            predicate = cb.conjunction();
        } else {
            predicate = cb.greaterThanOrEqualTo(root.get("count"), count);
        }

        return predicate;
    }

    private Predicate findByName(Root<Item> root, CriteriaBuilder cb){
        Predicate predicate;

        if (name == null){
            predicate = cb.conjunction();
        } else{
            predicate = cb.like(root.get("name"), '%' + name + '%');
        }

        return predicate;
    }

    private Predicate findByShop(Root<Item> root, CriteriaBuilder cb){
        Predicate predicate;

        final Join<Item, Shop> shopJoin = root.join("shop");

        return cb.equal(shopJoin.get("id"), shopId);


    }

}
