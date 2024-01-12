package com.example.spring_shopping.orderDetail;


import com.example.spring_shopping.items.Item;
import com.example.spring_shopping.orders.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderDetail{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long orderPrice;

    @Column
    private long count;

    @Column
    private LocalDateTime createDate;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="item_id")
    private Item item;   // 주문아이템




    // 주문아이템 : 주문 = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="orders_id")
    private Orders orders;   // 주문정보




    @Builder
    public OrderDetail(long orderPrice, long count, Item item, Orders orders){
        this.orderPrice = orderPrice;
        this.count =count;
        this.item.removeQuantity(count);
        this.orders = orders;
        this.createDate = LocalDateTime.now();
    }

}