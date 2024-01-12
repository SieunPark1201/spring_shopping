package com.example.spring_shopping.member;


import com.example.spring_shopping.orders.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;

    @Embedded
    private Address address;

    @Column
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Orders> memberOrders;

    @Builder
    public Member(Long id, String name, String email, String password, Address address){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.createDate = LocalDateTime.now();
    }

}