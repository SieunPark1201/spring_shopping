package com.example.spring_shopping.items;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String name;

    @Column
    private Long price;

    @Column
    private Long stockQuantity;





    @Builder
    public Item(Long id, String name, Long price, Long stockQuantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }


    public void removeQuantity(long count) {
        stockQuantity -= count;
    }

    public void addQuantity(long count) {
        stockQuantity+= count;
    }

    public void updateItem(long price, long stackQuantity){
        this.price = price;
        this.stockQuantity = stackQuantity;

    }
}