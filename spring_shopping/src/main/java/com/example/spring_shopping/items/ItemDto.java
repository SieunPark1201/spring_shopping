package com.example.spring_shopping.items;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class ItemDto{
    private Long id;
    private String name;
    private Long price;
    private Long stockQuantity;

}