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
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "item_count_order_mapping",
            joinColumns = {@JoinColumn(name = "order_id" ,
                    referencedColumnName = "id")}
    )
    @MapKeyJoinColumn(name = "item_id")
    private Map<Item, Integer> itemsCountMap = new HashMap<>();

}
