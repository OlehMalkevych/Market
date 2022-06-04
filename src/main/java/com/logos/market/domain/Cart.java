package com.logos.market.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private User user;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_count_cart_mapping",
            joinColumns = {@JoinColumn(name = "cart_id" ,
                    referencedColumnName = "id")}
    )
    @MapKeyJoinColumn(name = "item_id")
    private Map<Item, Integer> itemsCountMap = new HashMap<>();
}
