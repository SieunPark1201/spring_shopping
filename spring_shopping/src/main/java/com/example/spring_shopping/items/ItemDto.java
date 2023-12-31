package com.example.spring_shopping.items;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {

    private Long id;
    private String name;
    private Long price;
    private Long stockQuantity;


}