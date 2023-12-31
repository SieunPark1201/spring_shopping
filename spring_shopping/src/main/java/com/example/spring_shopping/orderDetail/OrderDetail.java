package com.example.spring_shopping.orderDetail;


import com.example.spring_shopping.items.Item;
import com.example.spring_shopping.order.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private Long orderPrice;  // 주문가격
    @Column
    private Long count; // 주문수량




    // 주문아이템 : 아이템 = N : 1
    @ManyToOne(fetch = FetchType.LAZY)   // ★ 주문확인
    @JoinColumn(nullable = false, name="item_id")
    private Item item;   // 주문아이템




    // 주문아이템 : 주문 = N : 1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="order_id")
    private Order customerOrder;   // 주문정보



    @Column
    private LocalDateTime createDate;







    @Builder
    public OrderDetail(Long orderPrice, Long count, Item item, Order customerOrder) throws Exception {

        this.orderPrice = orderPrice;
        this.count = count;
        this.item = item;
        this.item.removeQuantity(count);
        this.customerOrder = customerOrder;
        this.createDate = LocalDateTime.now();


    }
}
