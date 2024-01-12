package com.example.spring_shopping.orderDetail;

import com.example.spring_shopping.items.Item;
import com.example.spring_shopping.orders.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDetailDto {

    private Long orderPrice;
    private Long count;
    private Item itemId;
    private Orders customerOrder;
}
