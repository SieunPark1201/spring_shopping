package com.example.spring_shopping.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDto {

    public Long id;
    private String name;
    private String email;
    private String password;
    private String city;
    private String street;
    private String zipcode;
    private Long ordercount;
    private Long total;

}