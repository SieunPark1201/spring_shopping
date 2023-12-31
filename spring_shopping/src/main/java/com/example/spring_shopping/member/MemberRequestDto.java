package com.example.spring_shopping.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

    public Long id;
    private String name;
    private String email;
    private String password;
    private String city;
    private String street;
    private String zipcode;


}
