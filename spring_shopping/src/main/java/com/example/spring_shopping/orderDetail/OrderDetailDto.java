package com.example.spring_shopping.orderDetail;


import com.example.spring_shopping.items.Item;
import com.example.spring_shopping.order.Order;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {

    private Long orderPrice;
    private Long count;
    private Item itemId;
    private Order customerOrder;
}
