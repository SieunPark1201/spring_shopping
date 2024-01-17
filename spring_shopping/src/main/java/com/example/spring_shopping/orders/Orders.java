package com.example.spring_shopping.orders;

import com.example.spring_shopping.member.Member;
import com.example.spring_shopping.orderDetail.OrderDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Orders{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private LocalDateTime orderDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "member_id")
    private Member member;



    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail = new ArrayList<>();



    // 상태 변경
    public void statusChange(){
        this.status = OrderStatus.CANCELED;
    }





    @Builder
    public Orders(Member member) throws Exception{

        this.status =OrderStatus.ORDERED;
        this.member = member;
        this.orderDate = LocalDateTime.now();
    }



}