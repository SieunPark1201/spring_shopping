package com.example.spring_shopping.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;



@Getter
@Setter
// 별도의 테이블로 존재하지 않고, 다른 entity에 첨부되는 것.
// Embedded와 한쌍
@Embeddable
@NoArgsConstructor
public class Address {

    private String city;
    private String zipcode;
    private String street;

    public Address (String city, String street, String zipcode ){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

}
