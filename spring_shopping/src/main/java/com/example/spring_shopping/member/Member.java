package com.example.spring_shopping.member;

import com.example.spring_shopping.order.Order;
import com.example.spring_shopping.orderDetail.OrderDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=50)
    private String name;

    @Column(length=50, unique = true)
    private String email;

    @Column(length=255)
    private String password;

    @Embedded
    private Address address;

    // Member를 조회할 때, Member의 id값을 가지고, Orders의 어떤 컬럼을 where조건을 주고
    // 조회할지에 대해서 mapping정보를 Member테이블에 알려주는 것
    // OneToMany 또는 ManyToOne을 할 때 fetch전략이라는게 있다. Memeber객체 입장에서 fetch전략은 즉시 Order객체를 조회할지 말지에 대한 선택
    // LAZY 즉시 로딩x -> 참조해서 사용할 때만 로딩o, EAGER 즉시 로딩o
    // N+1 이슈를 해결하기 위해서는 LAZY 사용 추천
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> customerOrders;

    @Column
    private LocalDateTime createDate;


    @Builder
    public Member(String name, String email, String password, Address address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createDate = LocalDateTime.now();

    }

}
