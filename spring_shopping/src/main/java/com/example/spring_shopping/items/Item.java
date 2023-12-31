package com.example.spring_shopping.items;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;

    @Column
    private Long stockQuantity;

    public void updateItem(Long price, Long stockQuantity){
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    @Builder
    public Item(String name, Long price, Long stockQuantity){
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;

    }


    public void removeQuantity(Long stockQuantity) throws Exception {
        Long new_quantity = this.stockQuantity - stockQuantity;
        this.stockQuantity = new_quantity;
        if(new_quantity<0){
            throw new Exception();
        }
    }

    public void AddQuantity(Long stockQuantity) {
        Long new_quantity = this.stockQuantity + stockQuantity;
        this.stockQuantity = new_quantity;
    }



}
