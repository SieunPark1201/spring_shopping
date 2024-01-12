package com.example.spring_shopping.member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;


@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Address {
    private String city;

    private int zipcode;

    private String street;


    public Address(String city, int zipcode, String street){
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }
}